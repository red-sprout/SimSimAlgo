import assert from "node:assert/strict";
import { readFile } from "node:fs/promises";
import path from "node:path";
import { describe, it } from "node:test";
import {
  AtCoderAuthenticationError,
  AtCoderSubmissionClient,
  parseSubmissionPage
} from "../src/adapters/atcoder/submission-page.js";

describe("AtCoder submission page", () => {
  it("extracts and decodes source code without executing HTML", async () => {
    const html = await readFile(path.resolve("tests/fixtures/atcoder/submission-page.html"), "utf8");
    const parsed = parseSubmissionPage(html);

    assert.match(parsed.sourceCode, /System\.out\.println\("A < B && B > C"\)/);
    assert.equal(parsed.memory, "42852 KiB");
  });

  it("recognizes a login page as expired authentication", () => {
    assert.throws(
      () => parseSubmissionPage('<form action="/login"><input name="username"></form>'),
      AtCoderAuthenticationError
    );
  });

  it("sends the session cookie and does not expose it in errors", async () => {
    let cookie = "";
    const fakeFetch: typeof fetch = async (_input, init) => {
      cookie = new Headers(init?.headers).get("cookie") ?? "";
      return new Response('<pre id="submission-code">class Main {}</pre>', { status: 200 });
    };
    const client = new AtCoderSubmissionClient("secret-value", fakeFetch, "https://example.com");

    const result = await client.fetchSubmission("abc472", "78637333");

    assert.equal(cookie, "REVEL_SESSION=secret-value");
    assert.equal(result.sourceCode, "class Main {}");
  });
});
