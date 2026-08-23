import "dotenv/config";
import path from "node:path";
import { fileURLToPath } from "node:url";
import { createAtCoderAdapter } from "../adapters/atcoder/index.js";
import { writeSubmission } from "../core/repository-writer.js";
import { GitPublisher } from "../core/git-publisher.js";
import { JsonSyncStateStore } from "../core/sync-state.js";

const dryRun = process.argv.includes("--dry-run");
const commit = process.env.GIT_COMMIT === "true";
const push = process.env.GIT_PUSH === "true";
if (push && !commit) throw new Error("GIT_PUSH=true requires GIT_COMMIT=true");
const account = process.env.ATCODER_ACCOUNT ?? "sprout6626";
const session = process.env.ATCODER_REVEL_SESSION;
if (!session) throw new Error("ATCODER_REVEL_SESSION is required");

const automationRoot = path.resolve(path.dirname(fileURLToPath(import.meta.url)), "../../..");
const repositoryRoot = path.resolve(process.env.REPOSITORY_ROOT ?? path.resolve(automationRoot, ".."));
const stateFile = path.resolve(process.env.SYNC_STATE_FILE ?? path.join(automationRoot, ".state", "sync.json"));
const stateStore = new JsonSyncStateStore(stateFile);
const previousState = await stateStore.get("atcoder", account);
const adapter = createAtCoderAdapter(session);
const submissions = await adapter.fetchAcceptedSubmissions(account, previousState?.cursor);

const results: Array<{ submissionId: string; problemId: string; changed: boolean }> = [];
const changedFiles: string[] = [];
for (const submission of submissions) {
  if (dryRun) {
    results.push({ submissionId: submission.submissionId, problemId: submission.problemId, changed: true });
  } else {
    const result = await writeSubmission(repositoryRoot, submission);
    results.push({ submissionId: submission.submissionId, problemId: submission.problemId, changed: result.changed });
    changedFiles.push(...result.changedFiles);
  }
}

let publishResult;
if (!dryRun && commit) {
  const message = submissions.length === 1
    ? `[AtCoder] ${submissions[0]!.problemId}: ${submissions[0]!.title}`
    : `[AtCoder] Sync ${submissions.length} accepted submissions`;
  publishResult = await new GitPublisher(repositoryRoot).publish(changedFiles, message, push);
}

if (!dryRun && submissions.length > 0) {
  const latest = submissions.at(-1)!;
  const nextCursor = String(Math.floor(new Date(latest.submittedAt).getTime() / 1_000) + 1);
  await stateStore.set("atcoder", account, {
    cursor: nextCursor,
    lastSubmissionId: latest.submissionId,
    updatedAt: new Date().toISOString()
  });
}

console.log(JSON.stringify({
  site: "atcoder",
  account,
  dryRun,
  cursor: previousState?.cursor ?? "0",
  discovered: submissions.length,
  changed: results.filter((result) => result.changed).length,
  ...(publishResult ? { git: publishResult } : {}),
  submissions: results
}, null, 2));
