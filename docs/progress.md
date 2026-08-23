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
