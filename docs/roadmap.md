# Roadmap

## Phase 0 — 기반 구성

- [x] 문서 구조 및 저장소 작업 규칙
- [x] TypeScript 프로젝트 골격
- [x] 공통 Submission 모델
- [x] 결정적 경로 계산과 안전한 파일 writer
- [x] 의존성 설치 및 테스트 통과

## Phase 1 — AtCoder MVP

- [x] 계정과 공개 제출 목록 접근 범위 확인
- [x] 제출 목록 fixture 확보
- [ ] 제출 상세 및 문제 페이지 fixture 확보
- [x] HTML source parser 구현 및 synthetic fixture 테스트
- [x] 신규 AC 탐지 state store 구현
- [x] AtCoder sync CLI 구현
- [ ] Git commit/push service 구현
- [x] 동일 sync 재실행 시 변경 없음 검증

## Phase 2 — n8n 운영

- [x] Docker Compose 작성
- [x] Schedule/Manual workflow 작성
- [x] workflow JSON export
- [x] worker 동시 실행 제한
- [ ] 인증 만료 및 parser 실패 알림
- [ ] 재시작 후 상태 유지 검증
- [x] worker image build 및 내부 HTTP sync 실동작 검증

## Phase 3 — Programmers

- [ ] 제출 기록 endpoint 및 인증 방식 조사
- [ ] 세션 수명과 재로그인 절차 검증
- [ ] fixture와 adapter 구현
- [ ] 공통 writer 및 n8n workflow 연결
