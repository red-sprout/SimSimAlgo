import assert from "node:assert/strict";
import { execFile } from "node:child_process";
import { mkdtemp, mkdir, writeFile } from "node:fs/promises";
import os from "node:os";
import path from "node:path";
import { promisify } from "node:util";
import { describe, it } from "node:test";
import { GitPublisher } from "../src/core/git-publisher.js";

const execFileAsync = promisify(execFile);

describe("Git publisher", () => {
  it("commits only explicitly provided files", async () => {
    const root = await mkdtemp(path.join(os.tmpdir(), "simsimalgo-git-"));
    await execFileAsync("git", ["init", "-q", "-b", "main"], { cwd: root });
    await execFileAsync("git", ["config", "user.name", "Test User"], { cwd: root });
    await execFileAsync("git", ["config", "user.email", "test@example.com"], { cwd: root });
    await writeFile(path.join(root, "README.md"), "initial\n");
    await execFileAsync("git", ["add", "README.md"], { cwd: root });
    await execFileAsync("git", ["commit", "-q", "-m", "initial"], { cwd: root });

    await mkdir(path.join(root, "AtCoder"));
    const included = path.join(root, "AtCoder", "main.cpp");
    await writeFile(included, "int main() {}\n");
    await writeFile(path.join(root, "unrelated.txt"), "do not stage\n");

    const result = await new GitPublisher(root).publish([included], "sync AtCoder", false);
    const committed = await execFileAsync("git", ["show", "--pretty=", "--name-only", "HEAD"], { cwd: root });
    const status = await execFileAsync("git", ["status", "--short"], { cwd: root });

    assert.equal(result.commitCreated, true);
    assert.equal(committed.stdout.trim(), "AtCoder/main.cpp");
    assert.match(status.stdout, /\?\? unrelated\.txt/);
  });

  it("rejects paths outside the repository", async () => {
    const root = await mkdtemp(path.join(os.tmpdir(), "simsimalgo-git-safe-"));
    const publisher = new GitPublisher(root);
    await assert.rejects(() => publisher.publish([path.join(root, "..", "secret")], "bad", false), /outside repository/);
  });

  it("does not absorb an unrelated file already staged by another process", async () => {
    const root = await mkdtemp(path.join(os.tmpdir(), "simsimalgo-git-staged-"));
    await execFileAsync("git", ["init", "-q", "-b", "main"], { cwd: root });
    await execFileAsync("git", ["config", "user.name", "Test User"], { cwd: root });
    await execFileAsync("git", ["config", "user.email", "test@example.com"], { cwd: root });
    await writeFile(path.join(root, "README.md"), "initial\n");
    await execFileAsync("git", ["add", "README.md"], { cwd: root });
    await execFileAsync("git", ["commit", "-q", "-m", "initial"], { cwd: root });
    const included = path.join(root, "included.txt");
    const unrelated = path.join(root, "unrelated.txt");
    await writeFile(included, "included\n");
    await writeFile(unrelated, "unrelated\n");
    await execFileAsync("git", ["add", "--", unrelated], { cwd: root });

    await new GitPublisher(root).publish([included], "only included", false);
    const committed = await execFileAsync("git", ["show", "--pretty=", "--name-only", "HEAD"], { cwd: root });
    assert.equal(committed.stdout.trim(), "included.txt");
    assert.match((await execFileAsync("git", ["status", "--short"], { cwd: root })).stdout, /A  unrelated\.txt/);
  });
});
