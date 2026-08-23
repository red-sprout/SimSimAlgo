# Progress

## 2026-08-24

### 완료

- 저장소 현황과 기존 BaekjoonHub 경로/커밋 방식 확인
- n8n은 orchestration, TypeScript 코드는 수집과 저장을 담당하도록 경계 결정
- 문서 및 `AGENTS.md` 골격 작성
- 공통 Submission 모델과 AtCoder 경로 규칙 작성
- 저장소 밖 쓰기를 방지하는 repository writer 초안 작성
- TypeScript typecheck 및 writer 테스트 2개 통과
- 외부 테스트 프레임워크를 제거하고 Node 내장 test runner로 전환
- npm audit 취약점 0건 확인

### 다음 작업

- 인증된 AtCoder 세션으로 제출 상세 접근 범위 확인
- 인증 응답으로 synthetic fixture의 selector 검증

### 미결정

- n8n 배포 위치
- GitHub 인증 방식(SSH deploy key 권장)
- n8n 실패 알림 채널

## 2026-08-24 — AtCoder metadata 조사

### 완료

- AtCoder 사용자 ID를 `sprout6626`으로 확정
- 공개 제출 metadata 11건 확인
- 최신 AC `78637333` 및 문제 `abc472_e` 확인
- AtCoder Problems API client와 실제 응답 기반 fixture 추가
- 공식 제출 상세의 비인증 HTTP 접근이 CloudFront 403임을 확인

### 판단

- 제출 감지는 AtCoder Problems API를 사용한다.
- source code 수집은 인증 세션을 별도 주입한다.
- 세션이 없어도 metadata 수집기와 테스트는 동작해야 한다.

## 2026-08-24 — AtCoder session client

### 완료

- `REVEL_SESSION` 환경변수를 사용하는 제출 상세 client 구현
- source HTML entity decoding과 memory parsing 구현
- 로그인 redirect, HTTP 401/403을 인증 오류로 구분
- cookie와 source를 출력하지 않는 세션 점검 CLI 추가
- synthetic 제출 상세 fixture와 인증 오류 테스트 추가

### 실제 세션 검증

- `.env`의 cookie 형식과 dotenv 로딩이 정상임을 확인
- 자동화 전용 User-Agent에서는 CloudFront 403 발생
- 동일 세션에 브라우저 호환 요청 헤더 적용 시 제출 상세 HTTP 200 확인

## 2026-08-24 — AtCoder sync pipeline

### 구현

- AC metadata, 문제 제목, 인증 source를 공통 Submission 모델로 결합
- source 요청 간 기본 1초 간격 적용
- 계정별 epoch cursor를 원자적으로 저장하는 JSON state store 추가
- `--dry-run`을 지원하는 AtCoder sync CLI 추가

### 실제 동기화 결과

- dry-run으로 AC 10건의 metadata와 source 수집 성공
- `AtCoder/abc456`, `AtCoder/abc472`, `AtCoder/zone2021`에 문제 10개 저장
- README와 source를 합쳐 파일 20개 생성
- cursor `1787405972` 저장
- 즉시 재실행 결과 `discovered: 0`, `changed: 0` 확인

## 2026-08-24 — Git publisher

- writer가 반환한 파일만 명시적으로 stage하는 Git publisher 구현
- 빈 diff에서는 commit 생략
- `GIT_COMMIT`, `GIT_PUSH`를 sync CLI에 연결
- repository 밖 경로와 `.git` 경로 stage 차단

## 2026-08-24 — n8n worker

- n8n과 sync 실행 권한을 분리한 내부 HTTP worker 추가
- n8n 10분 Schedule Trigger 및 Manual Trigger workflow export 추가
- Docker Compose 구문과 worker image build 성공
- worker health endpoint 정상 확인
- 기존 cursor를 Docker state volume으로 이관
- 실제 worker sync 결과 `discovered: 0`, `commitCreated: false`, `pushed: true`
- Deploy Key로 `origin/main` push 성공
## AtCoder 제출 자동화

- n8n Form Trigger에서 `contestId`, `problemId`, `language`, `sourceCode`를 입력한다.
- `sync-worker`가 임시 디렉터리에서 `oj submit`을 실행하고 제출 페이지의 판정을 폴링한다.
- `AC`일 때만 기존 저장기와 GitPublisher를 실행한다. `WA`·`RE`·인증 오류에서는 저장소를 변경하지 않는다.
- 워커 이미지에는 `online-judge-tools`가 포함된다. 세션은 `ATCODER_REVEL_SESSION` 환경변수로만 주입한다.

워크플로우 파일: `n8n/workflows/atcoder-sync.json` (AtCoder 기능 통합본)

## 전략 전환 — 로컬 제출 중심

- 기본 경로를 `MacBook에서 작성·테스트·제출 → N100에서 AC 재검증·Git 반영`으로 변경한다.
- n8n Form 기반 서버 제출은 보조/실험 경로로 유지한다.
- 다음 구현은 이벤트 webhook과 submission ID 재검증이며, 이벤트가 없어도 기존 주기 sync로 복구한다.
- 프로그래머스는 ActionCable/Turnstile 때문에 서버 제출을 기본화하지 않고, MacBook 웹 제출 후 동기화 어댑터를 우선 검증한다.

## AtCoder 공식 제출 목록 즉시 반영

- `submissions/me` contest별 HTML 파서와 인증 client 추가
- `POST /sync/atcoder/contest` 추가: `contestId`·`problemId`로 최신 AC를 공식 페이지에서 재검증
- `n8n/workflows/atcoder-sync.json`에 주기 sync·contest 폼·URL webhook·보조 제출 폼 통합
- 실제 `abc472/abc472_e/78637333` 조회로 source·README 생성 확인
- Docker worker 재빌드·재기동 및 health 확인
