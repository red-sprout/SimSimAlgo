import assert from "node:assert/strict";
import { describe, it } from "node:test";
import { parseOfficialSubmissionRows } from "../src/adapters/atcoder/contest-submissions.js";

describe("AtCoder official submissions page", () => {
  it("parses AC and non-AC rows without relying on AtCoder Problems", () => {
    const html = `<table><tr><th>header</th></tr><tr><td class="no-break"><time>2026-08-22 22:39:31+0900</time></td><td><a href="/contests/abc472/tasks/abc472_e">E - Odd Cycle</a></td><td>User</td><td><a>Java24 (OpenJDK 24.0.2)</a></td><td class="submission-score" data-id="78637333">450</td><td>2163 Byte</td><td><span class="label label-success" title="Accepted">AC</span></td><td>513 ms</td><td>91688 KiB</td><td><a href="/contests/abc472/submissions/78637333">Detail</a></td></tr><tr><td><time>2026-08-22 21:00:00+0900</time></td><td><a href="/contests/abc472/tasks/abc472_e">E - Odd Cycle</a></td><td>User</td><td>Java24</td><td>0</td><td>100 Byte</td><td><span class="label label-warning">RE</span></td><td>1 ms</td><td>2 KiB</td><td><a href="/contests/abc472/submissions/78600000">Detail</a></td></tr></table>`;
    const rows = parseOfficialSubmissionRows(html, "abc472");
    assert.equal(rows.length, 2);
    assert.deepEqual(rows[0], { submissionId: "78637333", contestId: "abc472", problemId: "abc472_e", title: "E - Odd Cycle", verdict: "AC", language: "Java24 (OpenJDK 24.0.2)", submittedAt: "2026-08-22 22:39:31+0900", executionTime: "513 ms", memory: "91688 KiB" });
    assert.equal(rows[1]?.verdict, "RE");
  });
});
