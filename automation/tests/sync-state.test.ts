import assert from "node:assert/strict";
import { mkdtemp, stat } from "node:fs/promises";
import os from "node:os";
import path from "node:path";
import { describe, it } from "node:test";
import { JsonSyncStateStore } from "../src/core/sync-state.js";

describe("JSON sync state", () => {
  it("persists account cursors in a private file", async () => {
    const root = await mkdtemp(path.join(os.tmpdir(), "simsimalgo-state-"));
    const file = path.join(root, ".state", "sync.json");
    const store = new JsonSyncStateStore(file);

    assert.equal(await store.get("atcoder", "sprout6626"), undefined);
    await store.set("atcoder", "sprout6626", {
      cursor: "1787405972", lastSubmissionId: "78637333", updatedAt: "2026-08-24T00:00:00Z"
    });

    assert.equal((await store.get("atcoder", "sprout6626"))?.lastSubmissionId, "78637333");
    assert.equal((await stat(file)).mode & 0o777, 0o600);
  });
});
