import { execFile } from "node:child_process";
import { createServer } from "node:http";
import path from "node:path";
import { fileURLToPath } from "node:url";
import { promisify } from "node:util";
import { clearDiscordAuthAlert, notifyDiscordAuthFailure } from "./core/discord-alert.js";
import { submitAtCoder, type AtCoderSubmitRequest } from "./core/atcoder-submit.js";
import type { Submission } from "./core/submission.js";
import { writeSubmission } from "./core/repository-writer.js";
import { GitPublisher } from "./core/git-publisher.js";

const execFileAsync = promisify(execFile);
const port = Number(process.env.PORT ?? "3000");
const root = path.resolve(path.dirname(fileURLToPath(import.meta.url)), "../..");
const alertStateFile = process.env.DISCORD_ALERT_STATE_FILE ?? "/state/discord-alert.json";
let running = false;
async function readJson(request: import("node:http").IncomingMessage): Promise<unknown> { let body = ""; for await (const chunk of request) body += chunk; if (body.length > 700_000) throw new Error("request body is too large"); return JSON.parse(body || "{}"); }

if (!Number.isInteger(port) || port < 1 || port > 65535) throw new Error("Invalid PORT");
await execFileAsync("git", ["config", "--global", "--add", "safe.directory", process.env.REPOSITORY_ROOT ?? "/repo"]);

const server = createServer(async (request, response) => {
  response.setHeader("content-type", "application/json; charset=utf-8");
  if (request.method === "GET" && request.url === "/health") {
    response.end(JSON.stringify({ ok: true, running }));
    return;
  }
  if (request.method === "POST" && request.url === "/submit/atcoder") {
    if (running) { response.statusCode = 409; response.end(JSON.stringify({ error: "job_already_running" })); return; } running = true;
    try {
      const input = await readJson(request) as Partial<AtCoderSubmitRequest>;
      if (typeof input.contestId !== "string" || typeof input.problemId !== "string" || typeof input.sourceCode !== "string") { response.statusCode = 400; response.end(JSON.stringify({ error: "contestId, problemId, sourceCode are required" })); return; }
      const submitted = await submitAtCoder(input as AtCoderSubmitRequest);
      if (submitted.verdict !== "AC") { response.statusCode = 422; response.end(JSON.stringify({ ...submitted, committed: false, pushed: false })); return; }
      const submission: Submission = { site: "atcoder", account: process.env.ATCODER_ACCOUNT ?? "default", submissionId: submitted.submissionId, contestId: input.contestId, problemId: input.problemId, title: submitted.title, verdict: "AC", language: submitted.language, sourceCode: input.sourceCode, submittedAt: new Date().toISOString(), problemUrl: `https://atcoder.jp/contests/${input.contestId}/tasks/${input.problemId}`, submissionUrl: submitted.submissionUrl };
      const written = await writeSubmission(process.env.REPOSITORY_ROOT ?? "/repo", submission); const publisher = new GitPublisher(process.env.REPOSITORY_ROOT ?? "/repo");
      const published = (process.env.GIT_COMMIT ?? "true") === "true" ? await publisher.publish(written.changedFiles, `[AtCoder] ${input.problemId}: ${submitted.title}`, (process.env.GIT_PUSH ?? "true") === "true") : { commitCreated: false, pushed: false };
      response.end(JSON.stringify({ ...submitted, committed: published.commitCreated, pushed: published.pushed, changedFiles: written.changedFiles }));
    } catch (error) {
      const failure = error as Error & { stderr?: string; code?: number }; const details = `${failure.stderr ?? ""}\n${failure.message}`;
      if (/AtCoderAuthenticationError|ATCODER_REVEL_SESSION|HTTP (401|403)/i.test(details)) { try { await notifyDiscordAuthFailure(alertStateFile, process.env.DISCORD_WEBHOOK_URL); } catch (alertError) { console.error(`Discord alert failed: ${(alertError as Error).message}`); } }
      response.statusCode = 500; response.end(JSON.stringify({ error: "submit_failed", message: failure.message }));
    } finally { running = false; }
    return;
  }
  if (request.method !== "POST" || request.url !== "/sync/atcoder") {
    response.statusCode = 404;
    response.end(JSON.stringify({ error: "not_found" }));
    return;
  }
  if (running) {
    response.statusCode = 409;
    response.end(JSON.stringify({ error: "sync_already_running" }));
    return;
  }

  running = true;
  try {
    const { stdout } = await execFileAsync(process.execPath, [path.join(root, "dist/src/cli/sync-atcoder.js")], {
      cwd: root,
      env: process.env,
      maxBuffer: 4 * 1024 * 1024
    });
    await clearDiscordAuthAlert(alertStateFile);
    response.end(stdout.trim());
  } catch (error) {
    const failure = error as Error & { stderr?: string; code?: number };
    console.error(failure.stderr || failure.message);
    const details = `${failure.stderr ?? ""}\n${failure.message}`;
    if (/AtCoderAuthenticationError|ATCODER_REVEL_SESSION|HTTP (401|403)/i.test(details)) {
      try { await notifyDiscordAuthFailure(alertStateFile, process.env.DISCORD_WEBHOOK_URL); }
      catch (alertError) { console.error(`Discord alert failed: ${(alertError as Error).message}`); }
    }
    response.statusCode = 500;
    response.end(JSON.stringify({ error: "sync_failed", exitCode: failure.code ?? 1 }));
  } finally {
    running = false;
  }
});

server.listen(port, "0.0.0.0", () => console.log(`sync-worker listening on ${port}`));
