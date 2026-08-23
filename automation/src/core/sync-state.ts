import { mkdir, readFile, rename, writeFile } from "node:fs/promises";
import path from "node:path";

export interface AccountSyncState {
  cursor: string;
  lastSubmissionId?: string;
  updatedAt: string;
}

interface SyncStateFile {
  version: 1;
  accounts: Record<string, AccountSyncState>;
}

const EMPTY_STATE: SyncStateFile = { version: 1, accounts: {} };

function accountKey(site: string, account: string): string {
  return `${site}:${account}`;
}

export class JsonSyncStateStore {
  constructor(private readonly filePath: string) {}

  private async readState(): Promise<SyncStateFile> {
    try {
      const parsed = JSON.parse(await readFile(this.filePath, "utf8")) as Partial<SyncStateFile>;
      if (parsed.version !== 1 || typeof parsed.accounts !== "object" || parsed.accounts === null) {
        throw new Error("Unsupported sync state format");
      }
      return parsed as SyncStateFile;
    } catch (error) {
      if ((error as NodeJS.ErrnoException).code === "ENOENT") return structuredClone(EMPTY_STATE);
      throw error;
    }
  }

  async get(site: string, account: string): Promise<AccountSyncState | undefined> {
    return (await this.readState()).accounts[accountKey(site, account)];
  }

  async set(site: string, account: string, value: AccountSyncState): Promise<void> {
    const state = await this.readState();
    state.accounts[accountKey(site, account)] = value;
    await mkdir(path.dirname(this.filePath), { recursive: true });
    const temporaryPath = `${this.filePath}.${process.pid}.tmp`;
    await writeFile(temporaryPath, `${JSON.stringify(state, null, 2)}\n`, { mode: 0o600 });
    await rename(temporaryPath, this.filePath);
  }
}
