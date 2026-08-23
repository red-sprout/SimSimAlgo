import assert from "node:assert/strict";
import { readFile } from "node:fs/promises";
import path from "node:path";
import { describe, it } from "node:test";
import {
  AtCoderProblemsClient,
  parseSubmissionMetadata
} from "../src/adapters/atcoder/atcoder-problems-client.js";

describe("AtCoder Problems client", () => {
  it("parses a real sprout6626 submission fixture", async () => {
    const raw = JSON.parse(
      await readFile(path.resolve("tests/fixtures/atcoder/submissions.json"), "utf8")
    ) as unknown[];
    const accepted = raw.map(parseSubmissionMetadata).filter((submission) => submission.result === "AC");

    assert.equal(accepted.length, 2);
    assert.equal(accepted.at(-1)?.id, "78637333");
    assert.equal(accepted.at(-1)?.problemId, "abc472_e");
  });

  it("builds an encoded incremental submissions request", async () => {
    let requestedUrl = "";
    const fakeFetch: typeof fetch = async (input) => {
      requestedUrl = String(input);
      return new Response("[]", { status: 200, headers: { "content-type": "application/json" } });
    };
    const client = new AtCoderProblemsClient(fakeFetch, "https://example.com/api");

    await client.fetchUserSubmissions("sprout+test", 1787400000);

    const url = new URL(requestedUrl);
    assert.equal(url.searchParams.get("user"), "sprout+test");
    assert.equal(url.searchParams.get("from_second"), "1787400000");
  });

  it("rejects malformed responses", () => {
    assert.throws(() => parseSubmissionMetadata({ id: "not-a-number" }), /Invalid id/);
  });
});
