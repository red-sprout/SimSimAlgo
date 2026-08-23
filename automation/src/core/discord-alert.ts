import { readFile, writeFile } from "node:fs/promises";

interface AlertState { active: boolean; lastNotifiedAt?: number }
const REMINDER_MS = 24 * 60 * 60 * 1000;
async function readState(filePath: string): Promise<AlertState> {
  try { return JSON.parse(await readFile(filePath, "utf8")) as AlertState; }
  catch (error) { if ((error as NodeJS.ErrnoException).code === "ENOENT") return { active: false }; throw error; }
}
export async function clearDiscordAuthAlert(filePath: string): Promise<void> { await writeFile(filePath, '{"active":false}\n', { mode: 0o600 }); }
export async function notifyDiscordAuthFailure(filePath: string, webhookUrl: string | undefined): Promise<void> {
  if (!webhookUrl) return;
  const state = await readState(filePath); const now = Date.now();
  if (state.active && state.lastNotifiedAt !== undefined && now - state.lastNotifiedAt < REMINDER_MS) return;
  const response = await fetch(webhookUrl, { method: "POST", headers: { "content-type": "application/json" }, body: JSON.stringify({ content: `⚠️ SimSimAlgo AtCoder 세션 인증 실패\n${new Date(now).toISOString()}\nREVEL_SESSION을 갱신한 뒤 n8n workflow를 다시 실행해 주세요.` }), signal: AbortSignal.timeout(10_000) });
  if (!response.ok) throw new Error(`Discord webhook failed with HTTP ${response.status}`);
  await writeFile(filePath, `${JSON.stringify({ active: true, lastNotifiedAt: now })}\n`, { mode: 0o600 });
}
