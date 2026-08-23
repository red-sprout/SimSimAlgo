import { mkdir, readFile, writeFile } from "node:fs/promises";
import type { Submission } from "./submission.js";
import { pathsForSubmission, type SubmissionPaths } from "./path-policy.js";

export interface WriteResult {
  changed: boolean;
  changedFiles: string[];
  paths: SubmissionPaths;
}

function renderReadme(submission: Submission): string {
  const metrics = [
    submission.executionTime ? `- 실행 시간: ${submission.executionTime}` : undefined,
    submission.memory ? `- 메모리: ${submission.memory}` : undefined
  ].filter(Boolean);

  return [
    `# ${submission.title}`,
    "",
    `- 사이트: ${submission.site}`,
    `- 문제: ${submission.problemUrl}`,
    `- 제출: ${submission.submissionUrl}`,
    `- 언어: ${submission.language}`,
    `- 제출 시각: ${submission.submittedAt}`,
    ...metrics,
    ""
  ].join("\n");
}

async function writeWhenChanged(filePath: string, content: string): Promise<boolean> {
  const normalized = content.endsWith("\n") ? content : `${content}\n`;
  try {
    if ((await readFile(filePath, "utf8")) === normalized) return false;
  } catch (error) {
    const code = (error as NodeJS.ErrnoException).code;
    if (code !== "ENOENT") throw error;
  }
  await writeFile(filePath, normalized, "utf8");
  return true;
}

export async function writeSubmission(
  repositoryRoot: string,
  submission: Submission
): Promise<WriteResult> {
  const paths = pathsForSubmission(repositoryRoot, submission);
  await mkdir(paths.directory, { recursive: true });

  const changedFiles: string[] = [];
  if (await writeWhenChanged(paths.readme, renderReadme(submission))) changedFiles.push(paths.readme);
  if (await writeWhenChanged(paths.source, submission.sourceCode)) changedFiles.push(paths.source);

  return { changed: changedFiles.length > 0, changedFiles, paths };
}
