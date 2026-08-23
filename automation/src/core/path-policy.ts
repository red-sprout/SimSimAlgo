import path from "node:path";
import type { Submission } from "./submission.js";
import { extensionForLanguage } from "./language.js";

const SAFE_SEGMENT = /^[\p{L}\p{N}_.-]+$/u;

function assertSafeSegment(value: string, label: string): void {
  if (!value || value === "." || value === ".." || !SAFE_SEGMENT.test(value)) {
    throw new Error(`Unsafe ${label}: ${JSON.stringify(value)}`);
  }
}

export interface SubmissionPaths {
  directory: string;
  readme: string;
  source: string;
}

export function pathsForSubmission(repositoryRoot: string, submission: Submission): SubmissionPaths {
  assertSafeSegment(submission.problemId, "problemId");

  let relativeDirectory: string;
  if (submission.site === "atcoder") {
    if (!submission.contestId) throw new Error("AtCoder submission requires contestId");
    assertSafeSegment(submission.contestId, "contestId");
    relativeDirectory = path.join("AtCoder", submission.contestId, submission.problemId);
  } else {
    relativeDirectory = path.join("프로그래머스", submission.problemId);
  }

  const directory = path.resolve(repositoryRoot, relativeDirectory);
  const root = path.resolve(repositoryRoot);
  if (directory !== root && !directory.startsWith(`${root}${path.sep}`)) {
    throw new Error("Submission path escaped repository root");
  }

  return {
    directory,
    readme: path.join(directory, "README.md"),
    source: path.join(directory, `main.${extensionForLanguage(submission.language)}`)
  };
}
