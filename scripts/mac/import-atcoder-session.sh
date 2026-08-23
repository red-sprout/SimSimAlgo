#!/usr/bin/env bash
set -euo pipefail

USER_HOME="$(cd ~ && pwd)"
CONFIG_ROOT="${XDG_CONFIG_HOME:-${USER_HOME}/.config}"
DATA_ROOT="${XDG_DATA_HOME:-${USER_HOME}/.local/share}"
ACC_FILE="${CONFIG_ROOT}/atcoder-cli-nodejs/session.json"
OJ_FILE="${DATA_ROOT}/online-judge-tools/cookie.jar"

if [[ ! -t 0 ]]; then
  IFS= read -r REVEL_SESSION
else
  read -r -s -p "AtCoder REVEL_SESSION (input hidden): " REVEL_SESSION
  printf '\n' >&2
fi

if [[ -z "${REVEL_SESSION}" || "${REVEL_SESSION}" == *$'\n'* || "${REVEL_SESSION}" == *';'* || "${REVEL_SESSION}" == *'"'* ]]; then
  echo "Invalid or empty REVEL_SESSION" >&2
  exit 2
fi

umask 077
mkdir -p "$(dirname "$ACC_FILE")" "$(dirname "$OJ_FILE")"
printf '{"cookies":["REVEL_SESSION=%s"]}\n' "$REVEL_SESSION" > "$ACC_FILE"
printf '#LWP-Cookies-2.0\nSet-Cookie3: REVEL_SESSION="%s"; path="/"; domain="atcoder.jp"; path_spec; secure; discard; HttpOnly=None; version=0\n' "$REVEL_SESSION" > "$OJ_FILE"

echo "AtCoder session imported for acc and oj. Files were written with mode 600." >&2
echo "Check with: oj login --check https://atcoder.jp/contests/abc086/tasks/abc086_a" >&2
