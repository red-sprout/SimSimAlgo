#!/usr/bin/env bash
set -euo pipefail

if [[ $# -lt 1 ]]; then
  echo "usage: $0 https://atcoder.jp/contests/{contestId}/tasks/{problemId} [submissionId]" >&2
  exit 2
fi

PROBLEM_URL="$1"
SUBMISSION_ID="${2:-}"
if [[ "$PROBLEM_URL" =~ /contests/([^/]+)/tasks/([^/?#]+) ]]; then
  CONTEST_ID="${BASH_REMATCH[1]}"
  PROBLEM_ID="${BASH_REMATCH[2]}"
else
  echo "Invalid AtCoder task URL" >&2
  exit 2
fi

WEBHOOK_URL="${N8N_ATCODER_WEBHOOK_URL:-http://127.0.0.1:5678/webhook/atcoder-contest-sync-event}"
if [[ -n "$SUBMISSION_ID" ]]; then
  BODY=$(printf '{"contestId":"%s","problemId":"%s","submissionId":"%s"}' "$CONTEST_ID" "$PROBLEM_ID" "$SUBMISSION_ID")
else
  BODY=$(printf '{"contestId":"%s","problemId":"%s"}' "$CONTEST_ID" "$PROBLEM_ID")
fi
curl --fail-with-body -sS -X POST "$WEBHOOK_URL" -H 'content-type: application/json' --data "$BODY"
printf '\n'
