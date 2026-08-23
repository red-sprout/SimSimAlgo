import { AtCoderAuthenticationError } from "./submission-page.js";

export interface OfficialSubmissionRow {
  submissionId: string;
  contestId: string;
  problemId: string;
  title: string;
  verdict: string;
  language: string;
  submittedAt: string;
  executionTime?: string;
  memory?: string;
}

const ENTITY_MAP: Record<string, string> = { amp: "&", apos: "'", gt: ">", lt: "<", nbsp: " ", quot: '"' };
function decode(value: string): string {
  return value.replace(/&(#(?:x[0-9a-f]+|\d+)|[a-z]+);/gi, (entity, body: string) => body.startsWith("#x") || body.startsWith("#X") ? String.fromCodePoint(Number.parseInt(body.slice(2), 16)) : body.startsWith("#") ? String.fromCodePoint(Number.parseInt(body.slice(1), 10)) : ENTITY_MAP[body.toLowerCase()] ?? entity);
}
function text(value: string): string { return decode(value.replace(/<[^>]*>/g, " ").replace(/\s+/g, " ").trim()); }

export function parseOfficialSubmissionRows(html: string, contestId: string): OfficialSubmissionRow[] {
  const rows: OfficialSubmissionRow[] = [];
  for (const row of html.match(/<tr\b[\s\S]*?<\/tr>/gi) ?? []) {
    const detail = /href=["']\/contests\/[^/]+\/submissions\/(\d+)["']/i.exec(row);
    const task = /href=["']\/contests\/[^/]+\/tasks\/([^"'&?#]+)["'][^>]*>([\s\S]*?)<\/a>/i.exec(row);
    const status = /<span\b[^>]*class=["'][^"']*label-[^"']*["'][^>]*>([\s\S]*?)<\/span>/i.exec(row);
    const time = /<time\b[^>]*>([\s\S]*?)<\/time>/i.exec(row);
    if (!detail?.[1] || !task?.[1] || !status?.[1] || !time?.[1]) continue;
    const cells = [...row.matchAll(/<td\b[^>]*>([\s\S]*?)<\/td>/gi)].map((match) => text(match[1] ?? ""));
    const taskId = task[1];
    const taskTitle = task[2] ?? "";
    const submissionTime = time[1] ?? "";
    const statusText = text(status[1]).toUpperCase();
    const statusIndex = cells.findIndex((cell) => cell.toUpperCase() === statusText);
    rows.push({ submissionId: detail[1], contestId, problemId: decode(taskId), title: text(taskTitle), verdict: statusText, language: cells[3] ?? "", submittedAt: text(submissionTime), ...(statusIndex >= 0 && /ms$/i.test(cells[statusIndex + 1] ?? "") ? { executionTime: cells[statusIndex + 1] } : {}), ...(statusIndex >= 0 && /kib$/i.test(cells[statusIndex + 2] ?? "") ? { memory: cells[statusIndex + 2] } : {}) });
  }
  return rows;
}

export class AtCoderContestSubmissionsClient {
  constructor(private readonly revelSession: string, private readonly fetchImpl: typeof fetch = fetch, private readonly baseUrl = "https://atcoder.jp") {
    if (!revelSession.trim()) throw new AtCoderAuthenticationError("ATCODER_REVEL_SESSION is empty");
    if (/[\r\n;]/.test(revelSession)) throw new Error("Invalid ATCODER_REVEL_SESSION value");
  }
  async fetchContestSubmissions(contestId: string): Promise<OfficialSubmissionRow[]> {
    const url = new URL(`/contests/${encodeURIComponent(contestId)}/submissions/me`, this.baseUrl); url.searchParams.set("lang", "en");
    const response = await this.fetchImpl(url, { headers: { accept: "text/html,application/xhtml+xml", "accept-language": "en-US,en;q=0.9", cookie: `REVEL_SESSION=${this.revelSession}`, "user-agent": "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 Chrome/140 Safari/537.36" }, redirect: "follow", signal: AbortSignal.timeout(20_000) });
    if (response.status === 401 || response.status === 403 || response.url.includes("/login")) throw new AtCoderAuthenticationError(`AtCoder rejected the session with HTTP ${response.status}`);
    if (!response.ok) throw new Error(`AtCoder submissions request failed: HTTP ${response.status}`);
    return parseOfficialSubmissionRows(await response.text(), contestId);
  }
}
