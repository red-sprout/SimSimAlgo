# AtCoder research

## 목표

로그인 또는 특정 데스크톱 브라우저 확장 프로그램에 의존하지 않고 사용자의 신규 AC 제출과 소스 코드를 가져올 수 있는지 확인한다.

## 확인할 항목

- 사용자 제출 목록 URL과 pagination
- 제출 상세의 source code 공개 범위
- 대회 진행 중 제출 접근 제한
- contest ID, task ID, 언어, 실행 시간, 메모리 selector
- 문제 제목과 본문 접근 방식
- 로그인 필요 여부와 세션 만료 처리
- 요청 간격 및 서비스 정책

## 구현 방침

- 실제 페이지 parser를 작성하기 전에 HTML fixture를 확보한다.
- selector는 가능한 한 의미 있는 table heading과 element id를 기준으로 한다.
- 제출 ID를 증분 cursor로 사용하되, 누락 복구를 위해 최근 범위를 겹쳐 조회한다.
- AC가 아닌 제출은 저장하지 않는다.

## 2026-08-24 조사 결과 (`sprout6626`)

- AtCoder 사용자 프로필은 로그인 없이 접근 가능하다.
- AtCoder Problems API의 `/v3/user/submissions`에서 공개 제출 메타데이터 11건을 확인했다.
- 응답에는 submission ID, contest/problem ID, 언어, 판정, 점수, 코드 길이, 실행 시간이 있다.
- 문제 정적 카탈로그 `problems.json`에서 문제 index와 제목을 얻을 수 있다.
- 최신 제출은 `78637333`, `abc472_e`, `AC`, `Java24 (OpenJDK 24.0.2)`였다.
- 공식 제출 상세 URL은 자동화 전용 User-Agent 요청에 CloudFront 403을 반환했다.
- 따라서 목록 및 문제 metadata는 공개 API로, source code는 인증된 AtCoder 세션으로 가져오는 혼합 방식이 필요하다.

AtCoder Problems API는 AtCoder 공식 API가 아닌 커뮤니티 서비스이므로 장애와 schema 변경을 감지해야 한다. polling cursor는 API의 `from_second`를 사용하고 최근 구간을 겹쳐 조회한다.

## 다음 검증

- [확인] 인증된 `REVEL_SESSION`과 브라우저 호환 요청 헤더로 제출 상세 HTTP 200
- 상세 HTML의 source code와 memory selector
- cookie 만료를 식별할 수 있는 상태 코드 또는 로그인 redirect
