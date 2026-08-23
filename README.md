# 심심알고

개인 알고리즘 풀이 저장소입니다. 문제를 풀고, 풀이를 쌓고, 다시 찾아보기 쉽게 정리합니다.

- GitHub: [red-sprout/SimSimAlgo](https://github.com/red-sprout/SimSimAlgo)
- 풀이 계정: `red-sprout`, `sprout6626`
- 과거 풀이 저장소: `coding_test_study`

## 풀이 목록

| 사이트 | 경로 | 비고 |
| --- | --- | --- |
| 프로그래머스 | [프로그래머스](./프로그래머스) | SQL · C++ |
| 백준 | [백준](./백준) | C++ |
| AtCoder | [AtCoder](./AtCoder) | C++ |

각 문제는 난이도별 디렉터리 또는 대회별 디렉터리에 문제 설명과 풀이를 함께 둡니다.

## 자동화

n8n이 제출 결과를 확인하고, AC인 풀이만 저장한 뒤 Git commit/push까지 수행합니다. 작성·로컬 테스트·제출은 MacBook에서 수행하는 로컬 우선 구조입니다.

```text
n8n (10분 주기)
  └─ sync-worker
       ├─ AtCoder Accepted 제출 조회
       ├─ 문제 설명·소스 저장
       ├─ Git commit / push
       └─ 인증 오류 시 Discord 알림
```

현재 자동 동기화는 AtCoder부터 운영 중이며, 프로그래머스는 인증·제출 구조를 조사하며 연동 방식을 정리하고 있습니다.


```mermaid
flowchart LR
    M[MacBook<br/>작성·테스트·제출] --> N[n8n webhook]
    S[스케줄 fallback<br/>10분마다] --> N
    N --> W[sync-worker]
    W --> A[AtCoder<br/>제출·문제·소스 조회]
    A --> V{새 풀이인가?}
    V -- 예 --> R[README·소스 저장]
    R --> G[Git commit / push]
    V -- 아니오 --> D[변경 없음]
    W -. 인증 실패 .-> C[Discord @everyone 알림]
    W -. 성공 .-> X[알림 상태 초기화]
```

AtCoder는 MacBook에서 `atcoder-cli`/`oj`로 제출하는 것을 기본으로 합니다. `n8n/workflows/atcoder-submit.json`의 제출 폼은 서버 제출을 시험하기 위한 보조 경로입니다. 어떤 경로든 worker가 AC를 다시 확인한 경우에만 풀이 파일을 만들고 commit/push합니다.

제출 직후 바로 반영하려면 `n8n/workflows/atcoder-contest-sync.json`을 import하고 contest ID와 problem ID를 입력합니다. worker가 해당 contest의 공식 `submissions/me`에서 최신 AC를 확인하므로 AtCoder Problems API 지연을 기다리지 않아도 됩니다.


### 운영 문서

- [진행 상황](./docs/progress.md)
- [운영 가이드](./docs/operations.md)
- [아키텍처](./docs/architecture.md)
- [자동화 로드맵](./docs/roadmap.md)
- [보안 원칙](./docs/security.md)
- [프로그래머스 조사 기록](./docs/research/programmers.md)
- [AtCoder 조사 기록](./docs/research/atcoder.md)

### 로컬 자동화 실행

```bash
cd automation
npm install
npm test
npm run sync:atcoder -- --dry-run
```

실제 n8n 배포와 세션 설정은 [운영 가이드](./docs/operations.md)를 참고합니다. 세션 쿠키, Webhook URL, SSH 키 같은 민감한 값은 저장소에 커밋하지 않습니다.

## 디렉터리 구조

```text
.
├── 프로그래머스/       # 프로그래머스 풀이
├── 백준/               # 백준 풀이
├── AtCoder/            # AtCoder 풀이
├── automation/         # 사이트 어댑터·동기화 worker
├── n8n/                # n8n compose와 workflow
└── docs/               # 설계·운영·조사 문서
```

## 라이선스

개인 학습 기록을 위한 저장소입니다. 문제 원문과 플랫폼 제공 콘텐츠의 저작권은 각 서비스에 있습니다.
