# OAuth 공통 에러 타입 추가

## Background
소셜 로그인(Google, Kakao) 구현을 위해 eolma-common에 OAuth 관련 에러 타입을 추가한다. 모든 서비스가 공유하는 ErrorType enum에 새 항목을 정의해야 하며, eolma-user 서비스에서 소셜 로그인 처리 시 이 에러 타입들을 사용한다.

## Objective
ErrorType enum에 OAuth 관련 에러 코드 3개를 추가하여, 소셜 로그인 시 발생할 수 있는 비즈니스 예외를 표준 ProblemDetail 형식으로 처리한다.

## Requirements

### Functional Requirements
- FR-1: 동일 이메일이 다른 OAuth provider로 이미 가입된 경우 409 에러 반환
- FR-2: OAuth provider 통신 실패 시 502 에러 반환
- FR-3: LOCAL 계정 연결 시 비밀번호 미제공 시 400 에러 반환

## Out of Scope
- OAuth 로직 자체 (eolma-user에서 구현)
- 프론트엔드 에러 처리

## Technical Approach

기존 ErrorType enum 패턴을 그대로 따른다. `// OAuth` 섹션 주석 아래에 3개 항목 추가.

### Affected Files
- `src/main/java/com/eolma/common/exception/ErrorType.java` - OAuth 에러 타입 3개 추가

## Implementation Items
- [x] ErrorType.java에 OAuth 에러 타입 추가:
  - `ACCOUNT_EXISTS_DIFFERENT_PROVIDER("/errors/account-exists-different-provider", "Account Exists With Different Provider", 409)`
  - `OAUTH_PROVIDER_ERROR("/errors/oauth-provider-error", "OAuth Provider Error", 502)`
  - `PASSWORD_REQUIRED_FOR_LINKING("/errors/password-required-for-linking", "Password Required For Linking", 400)`
- [x] mavenLocal 배포 (`./gradlew publishToMavenLocal`)

## Acceptance Criteria
- [x] AC-1: ErrorType에 3개 새 항목이 추가되어 컴파일 성공
- [x] AC-2: mavenLocal 배포 후 eolma-user에서 참조 가능

## Notes
- eolma-user의 oauth-backend 세션보다 먼저 완료해야 함 (의존성)
- 기존 에러 타입 네이밍 컨벤션 준수: snake_case enum, kebab-case URI
