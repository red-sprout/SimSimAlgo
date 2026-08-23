export type SupportedSite = "atcoder" | "programmers";

export interface Submission {
  site: SupportedSite;
  account: string;
  submissionId: string;
  contestId?: string;
  problemId: string;
  title: string;
  verdict: "AC";
  language: string;
  sourceCode: string;
  submittedAt: string;
  problemUrl: string;
  submissionUrl: string;
  executionTime?: string;
  memory?: string;
}

export interface SiteAdapter {
  readonly site: SupportedSite;
  fetchAcceptedSubmissions(account: string, cursor?: string): Promise<Submission[]>;
}
