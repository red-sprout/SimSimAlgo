# Operations

## 예정 실행 방식

- n8n이 5~10분 간격으로 sync CLI를 실행한다.
- 같은 저장소에 대한 workflow 동시 실행은 1개로 제한한다.
- 결과는 `changed`, `unchanged`, `failed`로 구분한다.
- 일시적인 네트워크 오류만 지수 backoff로 재시도한다.
- 인증 실패와 parser 실패는 자동 재시도 횟수를 제한하고 운영자에게 알린다.

## AtCoder 세션 점검

`automation/.env.example`을 `automation/.env`로 복사한 후 값을 로컬에서 입력한다. `.env`는 Git ignore 대상이다.

```bash
cd automation
cp .env.example .env
# 편집기로 .env의 ATCODER_REVEL_SESSION 입력
npm run check:atcoder-session
```

정상 응답은 `authenticated`, submission/problem ID, source length만 출력하며 source와 cookie는 출력하지 않는다. 세션이 만료되면 `AtCoderAuthenticationError`로 실패한다.

`REVEL_SESSION`은 로그인한 브라우저의 개발자 도구에서 Cookie 저장소를 통해 확인할 수 있으며 특정 브라우저에 종속되지 않는다. 장기적으로는 n8n Credential에서 주입한다.

## AtCoder 동기화

```bash
cd automation
npm run sync:atcoder -- --dry-run
npm run sync:atcoder
```

첫 명령은 source까지 검증하지만 파일과 cursor를 변경하지 않는다. 실제 실행은 저장소의 `AtCoder/` 아래에 파일을 쓰고 `automation/.state/sync.json`에 다음 조회 시각을 원자적으로 기록한다.

n8n 전용 clone에서는 `.env` 또는 n8n 환경변수로 다음을 활성화한다.

```dotenv
GIT_COMMIT=true
GIT_PUSH=true
```

Git publisher는 writer가 이번 실행에서 변경한 경로만 `git add -- <paths>`로 stage한다. 다른 작업 파일은 자동 커밋하지 않는다.

## 장애 대응 원칙

- 인증 실패: credential을 갱신하고 수동 workflow로 재실행한다.
- parser 실패: 응답 fixture를 민감정보 제거 후 저장하고 parser 및 테스트를 함께 수정한다.
- Git 충돌: fetch/rebase 후 제한 횟수만 재시도한다. 강제 push하지 않는다.
- 부분 파일 생성: Git commit 전에 writer 결과를 검증하고 실패한 실행은 상태 저장소에 완료로 기록하지 않는다.
