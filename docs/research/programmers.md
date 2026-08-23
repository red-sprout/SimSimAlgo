# Programmers research

## 상태

AtCoder MVP 완료 후 기술 검증을 시작한다. 공식적으로 보장되지 않은 내부 API를 사용할 가능성이 있으므로 experimental adapter로 취급한다.

## 확인할 항목

- 로그인 상태에서 제출 목록을 조회하는 요청
- 제출 코드와 문제 metadata endpoint
- Cookie/session만으로 headless HTTP 재호출 가능한지 여부
- 소셜 로그인, CAPTCHA, 2단계 인증의 영향
- 세션 수명과 만료 식별 방법
- 문제 레벨 및 언어 표기 방식

## 구현 방침

- 필요하면 Playwright로 최초 로그인 세션만 생성한다.
- 주기 수집은 가능하면 HTTP client로 수행한다.
- 인증 만료 시 자동 우회하지 않고 재로그인 알림을 보낸다.
