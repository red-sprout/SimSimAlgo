# Repository agent guide

## Scope

- Problem solutions are existing user data. Do not reformat, rename, or move them as part of automation work.
- Put site integration code under `automation/` and exported n8n workflows under `n8n/workflows/`.
- Record meaningful implementation progress in `docs/progress.md`.

## Automation rules

- Keep site-specific parsing behind an adapter boundary.
- Keep filesystem and Git behavior independent of individual sites.
- Every sync operation must be idempotent: processing the same submission twice must not create a second change.
- Add or update parser fixtures when site HTML parsing changes.
- Prefer offline fixture tests. Mark tests requiring real credentials or network access explicitly.
- Never commit cookies, browser storage state, passwords, access tokens, SSH private keys, or n8n credentials.
- Do not log source credentials or complete HTTP headers.

## Verification

- Run `npm test` and `npm run typecheck` from `automation/` after TypeScript changes.
- A live-site check is not a substitute for fixture tests.
