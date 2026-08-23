import { mkdtemp, rm, writeFile } from "node:fs/promises";
import os from "node:os";
import path from "node:path";
import { execFile } from "node:child_process";
import { promisify } from "node:util";
import { AtCoderAuthenticationError } from "../adapters/atcoder/submission-page.js";

const execFileAsync = promisify(execFile);
const TERMINAL = new Set(["AC", "WA", "TLE", "MLE", "RE", "CE", "OLE", "IE"]);
export interface AtCoderSubmitRequest { contestId: string; problemId: string; sourceCode: string; language?: string; title?: string; }
export interface AtCoderSubmitResult { verdict: string; submissionId: string; submissionUrl: string; language: string; title: string; }
function safe(value: string, name: string): string { if (!/^[A-Za-z0-9_.-]+$/.test(value)) throw new Error(`Invalid ${name}`); return value; }
function verdictFromHtml(html: string): string | undefined {
  const match = /class=["'][^"']*label-(?:success|danger|warning|default)[^"']*["'][^>]*>\s*([A-Z]{2,3})\s*</i.exec(html) ?? />\s*(AC|WA|TLE|MLE|RE|CE|OLE|IE|WJ|Judging|Waiting)\s*</i.exec(html);
  return match?.[1]?.toUpperCase();
}
function cookieJar(revelSession: string): string {
  if (!revelSession || /[\r\n;\"]/.test(revelSession)) throw new AtCoderAuthenticationError("ATCODER_REVEL_SESSION is empty or invalid");
  return `#LWP-Cookies-2.0\nSet-Cookie3: REVEL_SESSION=\"${revelSession}\"; path=\"/\"; domain=\"atcoder.jp\"; path_spec; secure; discard; HttpOnly=None; version=0\n`;
}
async function fetchVerdict(contestId: string, submissionId: string, revelSession: string): Promise<string | undefined> {
  const response = await fetch(`https://atcoder.jp/contests/${encodeURIComponent(contestId)}/submissions/${encodeURIComponent(submissionId)}`, { headers: { cookie: `REVEL_SESSION=${revelSession}`, "user-agent": "SimSimAlgo automation" }, redirect: "follow", signal: AbortSignal.timeout(20_000) });
  if (response.status === 401 || response.status === 403 || response.url.includes("/login")) throw new AtCoderAuthenticationError(`AtCoder rejected the session with HTTP ${response.status}`);
  if (!response.ok) throw new Error(`AtCoder verdict request failed: HTTP ${response.status}`);
  return verdictFromHtml(await response.text());
}
export async function submitAtCoder(request: AtCoderSubmitRequest): Promise<AtCoderSubmitResult> {
  const contestId = safe(request.contestId, "contestId"); const problemId = safe(request.problemId, "problemId"); const language = request.language?.trim() || "Java";
  if (!/java/i.test(language)) throw new Error("Only Java submissions are enabled in the n8n form currently");
  if (!request.sourceCode.trim()) throw new Error("sourceCode must not be empty"); if (request.sourceCode.length > 512 * 1024) throw new Error("sourceCode is too large");
  const session = process.env.ATCODER_REVEL_SESSION ?? ""; const work = await mkdtemp(path.join(os.tmpdir(), "simsimalgo-submit-")); const cookie = path.join(work, "cookie.jar");
  const sourceName = "Main.java"; const url = `https://atcoder.jp/contests/${contestId}/tasks/${problemId}`;
  try {
    await writeFile(path.join(work, sourceName), request.sourceCode, "utf8"); await writeFile(cookie, cookieJar(session), { encoding: "utf8", mode: 0o600 });
    const result = await execFileAsync(process.env.OJ_BIN ?? "oj", ["-c", cookie, "submit", "-y", "--no-open", "-w", "0", url, sourceName], { cwd: work, env: process.env, maxBuffer: 4 * 1024 * 1024 });
    const output = `${result.stdout}\n${result.stderr}`; const submissionMatch = new RegExp(`/contests/${contestId}/submissions/(\\d+)`).exec(output);
    if (!submissionMatch?.[1]) { if (/login required|not logged in|403|401/i.test(output)) throw new AtCoderAuthenticationError("AtCoder rejected the session"); throw new Error(`oj did not return a submission URL: ${output.trim().slice(-1000)}`); }
    const submissionId = submissionMatch[1]; let verdict: string | undefined; const deadline = Date.now() + Number(process.env.ATCODER_VERDICT_TIMEOUT_MS ?? 120_000);
    while (Date.now() < deadline) { verdict = await fetchVerdict(contestId, submissionId, session); if (verdict && TERMINAL.has(verdict)) break; await new Promise((resolve) => setTimeout(resolve, 3_000)); }
    if (!verdict || !TERMINAL.has(verdict)) throw new Error(`AtCoder verdict timeout for ${submissionId}`);
    return { verdict, submissionId, submissionUrl: `https://atcoder.jp/contests/${contestId}/submissions/${submissionId}`, language, title: request.title?.trim() || problemId };
  } finally { await rm(work, { recursive: true, force: true }); }
}
