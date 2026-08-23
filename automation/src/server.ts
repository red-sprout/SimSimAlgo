import { execFile } from "node:child_process";
import { createServer } from "node:http";
import path from "node:path";
import { fileURLToPath } from "node:url";
import { promisify } from "node:util";

const execFileAsync = promisify(execFile);
const port = Number(process.env.PORT ?? "3000");
const root = path.resolve(path.dirname(fileURLToPath(import.meta.url)), "../..");
let running = false;

if (!Number.isInteger(port) || port < 1 || port > 65535) throw new Error("Invalid PORT");
await execFileAsync("git", ["config", "--global", "--add", "safe.directory", process.env.REPOSITORY_ROOT ?? "/repo"]);

const server = createServer(async (request, response) => {
  response.setHeader("content-type", "application/json; charset=utf-8");
  if (request.method === "GET" && request.url === "/health") {
    response.end(JSON.stringify({ ok: true, running }));
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
    response.end(stdout.trim());
  } catch (error) {
    const failure = error as Error & { stderr?: string; code?: number };
    console.error(failure.stderr || failure.message);
    response.statusCode = 500;
    response.end(JSON.stringify({ error: "sync_failed", exitCode: failure.code ?? 1 }));
  } finally {
    running = false;
  }
});

server.listen(port, "0.0.0.0", () => console.log(`sync-worker listening on ${port}`));
