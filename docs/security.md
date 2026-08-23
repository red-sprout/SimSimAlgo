# Security

- 사이트 비밀번호, cookie, token, Playwright storage state는 Git에 저장하지 않는다.
- `automation/.env`는 로컬 점검용이며 ignore 대상이다. `.env.example`에는 빈 값만 둔다.
- n8n workflow export에는 credential ID만 존재해야 한다.
- GitHub push는 저장소 하나에만 쓰기 가능한 deploy key를 우선 사용한다.
- 로그에 Cookie, Authorization, Set-Cookie 및 원문 credential을 출력하지 않는다.
- fixture를 저장하기 전에 사용자 식별 정보와 세션 값을 제거한다.
- 컨테이너에는 필요한 저장소 디렉터리와 상태 volume만 mount한다.
- 사이트 요청 빈도를 낮게 유지하고 실패 시 무한 재시도하지 않는다.
