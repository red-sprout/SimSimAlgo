# Architecture

## 목표

브라우저 확장 프로그램에 의존하지 않고 PS 사이트의 새 AC 제출을 주기적으로 수집하여 문제 설명과 소스 코드를 Git에 반영한다.

1차 대상은 AtCoder이며, 공통 파이프라인 검증 후 프로그래머스를 연결한다.

## 구성

```text
n8n Schedule Trigger
  -> internal HTTP sync worker
  -> site adapter CLI
  -> normalized Submission[]
  -> repository writer
  -> Git commit/push
  -> execution result / alert
```

### n8n

- 실행 주기와 수동 실행 webhook을 관리한다.
- 동시 실행을 제한한다.
- 성공, 변경 없음, 실패를 구분하고 알림을 보낸다.
- 사이트 파싱이나 파일 경로 규칙은 갖지 않는다.
- 저장소, AtCoder cookie, Git Deploy Key에 직접 접근하지 않는다.

### Sync worker

- Docker 내부망의 POST `/sync/atcoder`만 제공하고 host port는 열지 않는다.
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
