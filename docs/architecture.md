# Architecture

## 목표

풀이 작성과 제출은 개발 머신에서 자연스럽게 수행하고, n8n/N100은 제출 결과 검증·수집·Git 반영을 담당한다. 특정 브라우저 확장 프로그램에는 의존하지 않는다.

1차 대상은 AtCoder이며, 공통 파이프라인 검증 후 프로그래머스를 연결한다.

## 구성

```text
MacBook (IntelliJ/CLI, local test, submit)
  -> submission event (site/account/submission id)
  -> n8n webhook or scheduled fallback
  -> sync-worker (official page/API verifies AC)
  -> normalized Submission
  -> repository writer
  -> Git commit/push
  -> Discord result/alert
```

주기 수집은 이벤트가 누락되어도 복구할 수 있는 fallback이다. 이벤트 payload의 `verdict`나 source를 신뢰하지 않고, worker가 사이트에서 다시 확인한 `submissionId`만 저장한다.

### n8n

- 로컬 제출 이벤트와 주기 fallback을 관리한다.
- 동시 실행을 제한한다.
- 성공, 변경 없음, 실패를 구분하고 알림을 보낸다.
- 사이트 파싱이나 파일 경로 규칙은 갖지 않는다.
- 저장소, AtCoder cookie, Git Deploy Key에 직접 접근하지 않는다.

### Sync worker

- Docker 내부망의 sync/submit-event API만 제공하고 host port는 열지 않는다.
- 동시에 하나의 sync만 허용하며 중복 요청에는 HTTP 409를 반환한다.
- 저장소와 상태 volume, 읽기 전용 Deploy Key는 worker에만 mount한다.

### Site adapter

- 제출 목록과 제출 상세를 사이트별로 수집한다.
- AC 제출만 공통 `Submission` 모델로 변환한다.
- HTML 파싱은 저장된 fixture로 회귀 테스트한다.

### Repository writer

- 공통 모델을 결정적인 경로와 파일 내용으로 변환한다.
- 기존 코드와 내용이 같으면 쓰기를 생략한다.
- 쓰기 대상이 저장소 루트 밖으로 벗어나지 못하게 한다.
- Git 작업은 파일 생성과 분리하여 실행한다.

### State store

초기 구현은 JSON 상태 파일을 사용할 수 있지만 운영 환경에서는 SQLite 또는 PostgreSQL을 사용한다. 키는 `(site, account, submissionId)`이며, source hash와 commit SHA를 함께 기록한다.

## 저장 규칙

AtCoder 기본 경로:

```text
AtCoder/{contestId}/{problemId}/README.md
AtCoder/{contestId}/{problemId}/main.{extension}
```

동일 문제를 다시 AC하면 소스 파일을 최신 제출로 갱신한다. 같은 source hash이면 커밋하지 않는다.

## 인증

- 공개 HTTP 요청을 우선 사용한다.
- 브라우저 로그인이 필요한 경우 Playwright는 세션 생성에만 사용한다.
- 주기 수집은 가능한 한 저장된 세션을 사용하는 HTTP 요청으로 수행한다.
- 세션과 credential은 n8n Credential 또는 Docker secret에 보관한다.

### 제출 위치

- AtCoder: MacBook에 `atcoder-cli`와 `oj`를 설치해 로컬 테스트 후 제출한다.
- Programmers: IntelliJ에서 작성·검증 후 공식 웹 제출을 우선 사용한다. 제출 UI가 ActionCable/Turnstile에 의존하므로 서버가 브라우저 제출을 흉내 내는 방식은 기본 경로로 삼지 않는다.
- MacBook은 제출용 세션, N100은 동기화용 세션을 별도로 보관할 수 있다.
