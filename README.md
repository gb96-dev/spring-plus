🚀 Spring Boot Expert 과제 완수 보고서 (Step 1 ~ 9)
Tutor's View: 본 프로젝트는 기존 코드의 버그를 수정하는 Level 1 단계부터, JPA 심화와 Spring Security를 적용하는 Level 2 단계까지 모든 미션을 완수한 결과물입니다.

🏗️ 단계별 상세 구현 내역
[Level 1. 필수 미션]
1. @Transactional의 이해: readOnly = true 설정으로 인해 할 일 저장(saveTodo) 시 데이터가 삽입되지 않던 버그를 찾아 수정했습니다.

2. JWT의 이해: 유저 정보에 nickname을 추가하고, 프론트엔드에서 활용할 수 있도록 JWT 토큰 내 클레임에 닉네임을 포함시켰습니다.

3. JPA의 이해 (다중 조건 검색): weather 조건과 수정일 기준의 기간 검색 기능을 JPQL을 활용하여 동적 쿼리로 구현했습니다.

4. 테스트 코드의 이해: 존재하지 않는 일정 조회 시 InvalidRequestException이 발생하는지 검증하는 컨트롤러 테스트 코드를 수정하여 통과시켰습니다.

5. AOP의 이해: AdminAccessLoggingAspect가 실행 전(@Before)에 동작하도록 수정하고, 관리자 권한 변경 시 로그가 정상적으로 남도록 리팩토링했습니다.

[Level 2. 심화 미션]
6. JPA Cascade 적용: 할 일을 생성한 유저가 담당자로 자동 등록되도록 CascadeType을 활용한 연관관계 편의 메서드 로직을 완성했습니다.

7. N+1 문제 해결: 댓글 목록 조회 시 발생하는 N+1 문제를 페치 조인(Fetch Join) 혹은 엔티티 그래프를 통해 해결하여 쿼리 성능을 최적화했습니다.

8. QueryDSL 도입: 기존 JPQL로 작성된 findByIdWithUser 로직을 QueryDSL로 전환하여 컴파일 시점의 타입 안정성을 확보했습니다.

9. Spring Security 전환: 기존의 수동 Filter와 Argument Resolver 방식을 Spring Security 아키텍처로 전면 개편하고, @AuthenticationPrincipal을 도입했습니다.

🚨 최종 단계 트러블슈팅 (Expert)
문제: 시큐리티 도입 후 AOP 로그에서 User ID가 null로 찍히고, 포스트맨 호출 시 400 에러 발생.

해결:

SecurityContextHolder를 참조하도록 AOP 로직 수정.

UserRole Enum의 오타 수정 및 대소문자 허용 로직(equalsIgnoreCase) 추가.

DTO 필드명(role)과 포스트맨 키값을 일치시켜 최종 200 OK 확인.
