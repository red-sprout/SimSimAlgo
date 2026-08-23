# Operations

## 실행 방식

- n8n이 5~10분 간격으로 sync CLI를 실행한다.
- 같은 저장소에 대한 workflow 동시 실행은 1개로 제한한다.
- 결과는 `changed`, `unchanged`, `failed`로 구분한다.
- 일시적인 네트워크 오류만 지수 backoff로 재시도한다.
- 인증 실패와 parser 실패는 자동 재시도 횟수를 제한하고 운영자에게 알린다.

## 권장 제출 흐름

1. MacBook에서 에디터로 풀이를 작성한다.
2. AtCoder는 `oj test`로 샘플을 확인한 뒤 `acc submit` 또는 `oj submit`으로 제출한다.
3. 제출 이벤트에는 사이트·계정·submission ID만 n8n에 전달한다. source와 AC 여부는 전달값을 신뢰하지 않는다.
4. worker가 공식 제출 페이지/API에서 AC와 source를 재검증한 뒤에만 writer와 GitPublisher를 실행한다.
5. 이벤트 전송이 실패해도 10분 주기 fallback sync가 누락된 AC를 수집한다.

현재 저장소의 `atcoder-submit` Form workflow는 서버 제출을 시험하기 위한 보조 경로이며, 기본 운영 경로는 로컬 제출이다.

## AtCoder 세션 점검

### MacBook 로그인

가장 먼저 공식 브라우저 로그인 방식부터 시도한다. `oj`가 로그인 브라우저를 열고 세션을 저장하며, `acc`는 이 `oj` 설정을 사용한다.

```bash
oj login --use-browser always https://atcoder.jp/
oj login --check https://atcoder.jp/contests/abc086/tasks/abc086_a
acc session
```

브라우저 자동 열기나 `acc login`이 실패하면 로그인한 브라우저의 AtCoder 쿠키에서 `REVEL_SESSION` 값만 복사한다. 특정 브라우저 확장 기능은 필요 없다. 값은 셸 history에 남기지 말고 다음 스크립트에 숨겨진 입력으로 붙여넣는다.

```bash
./scripts/mac/import-atcoder-session.sh
# 프롬프트에 REVEL_SESSION 붙여넣기
oj login --check https://atcoder.jp/contests/abc086/tasks/abc086_a
acc session
```

macOS에서는 `~/Library/Preferences/atcoder-cli-nodejs/session.json`과 `~/Library/Application Support/online-judge-tools/cookie.jar`를 사용하고, Linux에서는 XDG 기본 경로를 사용한다. 세션은 만료될 수 있으므로 실패하면 같은 절차로 다시 import한다.

Selenium 방식도 사용할 수 있지만 별도 설치가 필요하다.

```bash
python3 -m pip install --user selenium
oj login --use-browser always https://atcoder.jp/
```

macOS Safari WebDriver를 사용할 때는 Safari의 개발자 메뉴에서 원격 자동화를 허용해야 한다. 브라우저 드라이버 설정을 피하려면 위의 쿠키 import 방식을 사용한다.

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

## n8n Docker 시작

```bash
cd n8n
cp .env.example .env
openssl rand -hex 32
# 출력값을 .env의 N8N_ENCRYPTION_KEY에 입력
docker compose up -d --build
```

기존 로컬 sync cursor가 있다면 최초 1회 worker volume으로 복사해 과거 제출을 다시 조회하지 않게 한다.

```bash
docker compose cp ../automation/.state/sync.json sync-worker:/state/sync.json
```

브라우저에서 `http://127.0.0.1:5678`에 접속해 owner 계정을 만든다. `workflows/atcoder-sync.json`을 import하고 수동 실행으로 확인한 다음 workflow를 활성화한다.

worker는 host에 port를 공개하지 않는다. n8n만 내부 주소 `http://sync-worker:3000`으로 접근한다. Git 키, AtCoder cookie, 저장소 mount는 worker에만 제공한다.

현재 n8n은 SSH loopback 개발 접속만 허용하므로 `N8N_SECURE_COOKIE=false`로 설정되어 있다. 인터넷에 직접 노출하거나 HTTPS reverse proxy를 붙이는 배포에서는 이 설정을 제거하고 TLS를 사용해야 한다.

실행 기록은 `EXECUTIONS_DATA_PRUNE=true`로 자동 정리한다. 성공·실패 결과를 확인할 수 있도록 유지하되 최대 168시간(7일) 또는 1000건까지만 보관한다. 소스 코드 자체는 execution payload에 포함되지 않고 worker 응답의 요약만 반환된다.

## 장애 대응 원칙

- 인증 실패: credential을 갱신하고 수동 workflow로 재실행한다.
- parser 실패: 응답 fixture를 민감정보 제거 후 저장하고 parser 및 테스트를 함께 수정한다.
- Git 충돌: fetch/rebase 후 제한 횟수만 재시도한다. 강제 push하지 않는다.
- 부분 파일 생성: Git commit 전에 writer 결과를 검증하고 실패한 실행은 상태 저장소에 완료로 기록하지 않는다.
