import assert from "node:assert/strict";
import { describe, it } from "node:test";
import { AtCoderAdapter } from "../src/adapters/atcoder/index.js";

describe("AtCoder adapter", () => {
  it("combines accepted metadata, problem title, and source", async () => {
    let receivedCursor = -1;
    const adapter = new AtCoderAdapter({
      async fetchUserSubmissions(_account, cursor) {
        receivedCursor = cursor;
        return [{
          id: "42", epochSecond: 1_787_405_971, problemId: "abc472_e", contestId: "abc472",
          userId: "sprout6626", language: "Java24", point: 450, sourceLength: 20,
          result: "AC", executionTimeMs: 513
        }, {
          id: "41", epochSecond: 1_787_405_000, problemId: "abc472_d", contestId: "abc472",
          userId: "sprout6626", language: "Java24", point: 0, sourceLength: 10,
          result: "RE", executionTimeMs: 100
        }];
      },
      async fetchProblemCatalog() {
        return new Map([["abc472_e", {
          id: "abc472_e", contestId: "abc472", problemIndex: "E", name: "Odd Cycle", title: "E. Odd Cycle"
        }]]);
      }
    }, {
      async fetchSubmission() {
        return { sourceCode: "class Main {}\n", memory: "91688 KiB" };
      }
    }, { requestDelayMs: 0 });

    const submissions = await adapter.fetchAcceptedSubmissions("sprout6626", "1787400000");

    assert.equal(receivedCursor, 1_787_400_000);
    assert.equal(submissions.length, 1);
    assert.equal(submissions[0]?.title, "Odd Cycle");
    assert.equal(submissions[0]?.memory, "91688 KiB");
    assert.equal(submissions[0]?.verdict, "AC");
  });
});
