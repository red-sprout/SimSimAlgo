import type { SiteAdapter, Submission } from "../../core/submission.js";
import {
  AtCoderProblemsClient,
  type AtCoderProblemMetadata,
  type AtCoderSubmissionMetadata
} from "./atcoder-problems-client.js";
import { AtCoderSubmissionClient, type AtCoderSubmissionPage } from "./submission-page.js";
export * from "./atcoder-problems-client.js";
export * from "./submission-page.js";

interface MetadataClient {
  fetchUserSubmissions(account: string, fromEpochSecond: number): Promise<AtCoderSubmissionMetadata[]>;
  fetchProblemCatalog(): Promise<Map<string, AtCoderProblemMetadata>>;
}

interface SubmissionClient {
  fetchSubmission(contestId: string, submissionId: string): Promise<AtCoderSubmissionPage>;
}

export interface AtCoderAdapterOptions {
  requestDelayMs?: number;
  sleep?: (milliseconds: number) => Promise<void>;
}

function defaultSleep(milliseconds: number): Promise<void> {
  return new Promise((resolve) => setTimeout(resolve, milliseconds));
}

function parseCursor(cursor?: string): number {
  if (cursor === undefined) return 0;
  const value = Number(cursor);
  if (!Number.isSafeInteger(value) || value < 0) throw new Error(`Invalid AtCoder cursor: ${cursor}`);
  return value;
}

export class AtCoderAdapter implements SiteAdapter {
  readonly site = "atcoder" as const;
  private readonly requestDelayMs: number;
  private readonly sleep: (milliseconds: number) => Promise<void>;

  constructor(
    private readonly metadataClient: MetadataClient,
    private readonly submissionClient: SubmissionClient,
    options: AtCoderAdapterOptions = {}
  ) {
    this.requestDelayMs = options.requestDelayMs ?? 1_000;
    this.sleep = options.sleep ?? defaultSleep;
  }

  async fetchAcceptedSubmissions(account: string, cursor?: string): Promise<Submission[]> {
    const metadata = await this.metadataClient.fetchUserSubmissions(account, parseCursor(cursor));
    const accepted = metadata.filter((item) => item.result === "AC").sort((a, b) => a.epochSecond - b.epochSecond);
    if (accepted.length === 0) return [];

    const problems = await this.metadataClient.fetchProblemCatalog();
    const submissions: Submission[] = [];
    for (const [index, item] of accepted.entries()) {
      const problem = problems.get(item.problemId);
      if (!problem) throw new Error(`Problem metadata not found: ${item.problemId}`);
      if (index > 0 && this.requestDelayMs > 0) await this.sleep(this.requestDelayMs);
      const detail = await this.submissionClient.fetchSubmission(item.contestId, item.id);
      submissions.push({
        site: "atcoder",
        account,
        submissionId: item.id,
        contestId: item.contestId,
        problemId: item.problemId,
        title: problem.name,
        verdict: "AC",
        language: item.language,
        sourceCode: detail.sourceCode,
        submittedAt: new Date(item.epochSecond * 1_000).toISOString(),
        problemUrl: `https://atcoder.jp/contests/${item.contestId}/tasks/${item.problemId}`,
        submissionUrl: `https://atcoder.jp/contests/${item.contestId}/submissions/${item.id}`,
        ...(item.executionTimeMs === null ? {} : { executionTime: `${item.executionTimeMs} ms` }),
        ...(detail.memory ? { memory: detail.memory } : {})
      });
    }
    return submissions;
  }
}

export function createAtCoderAdapter(revelSession: string): AtCoderAdapter {
  return new AtCoderAdapter(new AtCoderProblemsClient(), new AtCoderSubmissionClient(revelSession));
}
