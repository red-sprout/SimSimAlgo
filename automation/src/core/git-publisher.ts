import path from "node:path";
import { spawn } from "node:child_process";

interface CommandResult {
  exitCode: number;
  stdout: string;
  stderr: string;
}

export interface PublishResult {
  commitCreated: boolean;
  pushed: boolean;
  commitSha?: string;
}

function run(command: string, args: string[], cwd: string): Promise<CommandResult> {
  return new Promise((resolve, reject) => {
    const child = spawn(command, args, { cwd, shell: false, stdio: ["ignore", "pipe", "pipe"] });
    let stdout = "";
    let stderr = "";
    child.stdout.setEncoding("utf8").on("data", (chunk: string) => { stdout += chunk; });
    child.stderr.setEncoding("utf8").on("data", (chunk: string) => { stderr += chunk; });
    child.on("error", reject);
    child.on("close", (exitCode) => resolve({ exitCode: exitCode ?? 1, stdout, stderr }));
  });
}

export class GitCommandError extends Error {
  constructor(readonly args: string[], readonly exitCode: number, stderr: string) {
    super(`git ${args[0] ?? "command"} failed (${exitCode}): ${stderr.trim()}`);
    this.name = "GitCommandError";
  }
}

export class GitPublisher {
  constructor(private readonly repositoryRoot: string) {}

  private async git(args: string[], allowedExitCodes: number[] = [0]): Promise<CommandResult> {
    const result = await run("git", args, this.repositoryRoot);
    if (!allowedExitCodes.includes(result.exitCode)) {
      throw new GitCommandError(args, result.exitCode, result.stderr);
    }
    return result;
  }

  private relativePath(filePath: string): string {
    const root = path.resolve(this.repositoryRoot);
    const absolute = path.resolve(filePath);
    const relative = path.relative(root, absolute);
    if (!relative || relative === ".git" || relative.startsWith(`.git${path.sep}`) || relative.startsWith(`..${path.sep}`) || path.isAbsolute(relative)) {
      throw new Error(`Refusing to stage path outside repository: ${filePath}`);
    }
    return relative;
  }

  async publish(filePaths: string[], message: string, push: boolean): Promise<PublishResult> {
    if (!message.trim()) throw new Error("Git commit message must not be empty");
    const relativePaths = [...new Set(filePaths.map((filePath) => this.relativePath(filePath)))];
    let commitCreated = false;

    if (relativePaths.length > 0) {
      await this.git(["add", "--", ...relativePaths]);
      const diff = await this.git(["diff", "--cached", "--quiet", "--"], [0, 1]);
      if (diff.exitCode === 1) {
        await this.git(["commit", "-m", message, "--", ...relativePaths]);
        commitCreated = true;
      }
    }

    if (push) await this.git(["push", "--porcelain", "origin", "HEAD"]);
    const sha = (await this.git(["rev-parse", "HEAD"])).stdout.trim();
    return { commitCreated, pushed: push, commitSha: sha };
  }
}
