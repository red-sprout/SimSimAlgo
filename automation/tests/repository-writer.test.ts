import { mkdtemp, readFile } from "node:fs/promises";
import os from "node:os";
import path from "node:path";
import assert from "node:assert/strict";
import { describe, it } from "node:test";
import { pathsForSubmission } from "../src/core/path-policy.js";
import { writeSubmission } from "../src/core/repository-writer.js";
import type { Submission } from "../src/core/submission.js";

const submission: Submission = {
  site: "atcoder",
  account: "red-sprout",
  submissionId: "12345678",
  contestId: "abc470",
  problemId: "abc470_a",
  title: "Example Problem",
  verdict: "AC",
  language: "C++ 23 (gcc 15.2)",
  sourceCode: "int main() { return 0; }\n",
  submittedAt: "2026-08-24T12:34:56+09:00",
  problemUrl: "https://atcoder.jp/contests/abc470/tasks/abc470_a",
  submissionUrl: "https://atcoder.jp/contests/abc470/submissions/12345678",
  executionTime: "1 ms",
  memory: "1024 KiB"
};

describe("repository writer", () => {
  it("writes an AtCoder submission and is idempotent", async () => {
    const root = await mkdtemp(path.join(os.tmpdir(), "simsimalgo-"));

    const first = await writeSubmission(root, submission);
    const second = await writeSubmission(root, submission);

    assert.equal(first.changed, true);
    assert.equal(first.changedFiles.length, 2);
    assert.equal(second.changed, false);
    assert.equal(await readFile(first.paths.source, "utf8"), submission.sourceCode);
    assert.match(await readFile(first.paths.readme, "utf8"), /# Example Problem/);
  });

  it("rejects path traversal in identifiers", () => {
    assert.throws(
      () => pathsForSubmission("/tmp/repository", { ...submission, problemId: "../escape" }),
      /Unsafe problemId/
    );
  });
});
