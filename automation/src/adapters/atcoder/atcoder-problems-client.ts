const DEFAULT_API_BASE_URL = "https://kenkoooo.com/atcoder/atcoder-api";
const DEFAULT_RESOURCES_BASE_URL = "https://kenkoooo.com/atcoder/resources";

export interface AtCoderSubmissionMetadata {
  id: string;
  epochSecond: number;
  problemId: string;
  contestId: string;
  userId: string;
  language: string;
  point: number;
  sourceLength: number;
  result: string;
  executionTimeMs: number | null;
}

export interface AtCoderProblemMetadata {
  id: string;
  contestId: string;
  problemIndex: string;
  name: string;
  title: string;
}

type FetchLike = typeof fetch;

function isRecord(value: unknown): value is Record<string, unknown> {
  return typeof value === "object" && value !== null;
}

function requiredString(value: unknown, field: string): string {
  if (typeof value !== "string" || value.length === 0) throw new Error(`Invalid ${field}`);
  return value;
}

function requiredNumber(value: unknown, field: string): number {
  if (typeof value !== "number" || !Number.isFinite(value)) throw new Error(`Invalid ${field}`);
  return value;
}

export function parseSubmissionMetadata(value: unknown): AtCoderSubmissionMetadata {
  if (!isRecord(value)) throw new Error("Invalid AtCoder submission");
  const executionTime = value.execution_time;
  if (executionTime !== null && executionTime !== undefined && typeof executionTime !== "number") {
    throw new Error("Invalid execution_time");
  }

  return {
    id: String(requiredNumber(value.id, "id")),
    epochSecond: requiredNumber(value.epoch_second, "epoch_second"),
    problemId: requiredString(value.problem_id, "problem_id"),
    contestId: requiredString(value.contest_id, "contest_id"),
    userId: requiredString(value.user_id, "user_id"),
    language: requiredString(value.language, "language"),
    point: requiredNumber(value.point, "point"),
    sourceLength: requiredNumber(value.length, "length"),
    result: requiredString(value.result, "result"),
    executionTimeMs: executionTime ?? null
  };
}

export function parseProblemMetadata(value: unknown): AtCoderProblemMetadata {
  if (!isRecord(value)) throw new Error("Invalid AtCoder problem");
  return {
    id: requiredString(value.id, "id"),
    contestId: requiredString(value.contest_id, "contest_id"),
    problemIndex: requiredString(value.problem_index, "problem_index"),
    name: requiredString(value.name, "name"),
    title: requiredString(value.title, "title")
  };
}

async function fetchJson(fetchImpl: FetchLike, url: URL): Promise<unknown> {
  const response = await fetchImpl(url, {
    headers: { accept: "application/json" },
    signal: AbortSignal.timeout(20_000)
  });
  if (!response.ok) throw new Error(`AtCoder data request failed: ${response.status} ${url.pathname}`);
  return response.json() as Promise<unknown>;
}

export class AtCoderProblemsClient {
  constructor(
    private readonly fetchImpl: FetchLike = fetch,
    private readonly apiBaseUrl = DEFAULT_API_BASE_URL,
    private readonly resourcesBaseUrl = DEFAULT_RESOURCES_BASE_URL
  ) {}

  async fetchUserSubmissions(account: string, fromEpochSecond: number): Promise<AtCoderSubmissionMetadata[]> {
    const url = new URL(`${this.apiBaseUrl}/v3/user/submissions`);
    url.searchParams.set("user", account);
    url.searchParams.set("from_second", String(fromEpochSecond));
    const json = await fetchJson(this.fetchImpl, url);
    if (!Array.isArray(json)) throw new Error("AtCoder submissions response must be an array");
    return json.map(parseSubmissionMetadata);
  }

  async fetchProblemCatalog(): Promise<Map<string, AtCoderProblemMetadata>> {
    const json = await fetchJson(this.fetchImpl, new URL(`${this.resourcesBaseUrl}/problems.json`));
    if (!Array.isArray(json)) throw new Error("AtCoder problem catalog must be an array");
    return new Map(json.map(parseProblemMetadata).map((problem) => [problem.id, problem]));
  }
}
