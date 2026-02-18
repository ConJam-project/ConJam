# ConJam Project Rules

## Project Overview
ConJam은 국내 콘서트 정보를 통합하여 보여주는 Android 앱입니다.
KOPIS(공연예술통합전산망) OpenAPI를 데이터 소스로 사용합니다.

## Git Workflow

### Issue Tracking
- 모든 작업은 GitHub Issue로 먼저 생성합니다.
- Issue 제목 형식: `feature : XX 기능 개발` 또는 `fix : XX 버그 수정`

### Branch Naming
- Issue 번호를 기반으로 브랜치를 생성합니다.
- 형식: `feature/#<이슈번호>` (예: `feature/#2`)

### Commit Convention
- 커밋 메시지에 이슈 번호를 포함합니다.
- 형식: `[#이슈번호] 커밋 메시지` (예: `[#2] Home 화면 UI 구현`)

### PR (Pull Request)
- **PR 전 프리뷰 확인 필수**: 코드 작성 후 빌드하여 사용자가 프리뷰 화면을 먼저 확인합니다. 사용자 확인 후에만 PR을 생성합니다.
- PR 제목 형식: `Feature : #<이슈번호> XX 기능 개발` (예: `Feature : #8 디자인 시스템 + Home 화면 개발`)
- PR 대상 브랜치: `develop`

### Merge Convention
- **Squash Merge** 방식으로 머지합니다.
- 머지 커밋 메시지는 **PR 제목**을 그대로 사용합니다.
- 머지 완료 후 해당 feature 브랜치는 **원격/로컬 모두 삭제**합니다.

## Tech Stack
- **Android**: Kotlin, Jetpack Compose, Material3
- **Architecture**: MVVM, Navigation Compose, StateFlow
- **Data Source**: KOPIS OpenAPI (공연예술통합전산망)
- **Backend** (예정): AWS Lambda
- **Auth** (예정): Google Login
- **Push** (예정): FCM

## Design System
- **Theme**: Dark theme 기본
- **Primary Color**: #7B61FF (보라)
- **Background**: #121212 (다크 그레이)
- **Card-based layout**: 포스터 이미지 강조

## Module Structure
- `app/` - Application entry point
- `feature/` - 화면별 기능 구현 (home, search, calendar, bookmark, setting)
- `core/data/` - 데이터 레이어 (추후 구현)
- `core/network/` - 네트워크 레이어 (추후 구현)
- `core/model/` - 공유 데이터 모델 (추후 구현)

## File Convention
각 feature 디렉토리는 다음 파일 구조를 따릅니다:
- `Route<Feature>.kt` - 네비게이션 라우트
- `<feature>NavGraph.kt` - 네비게이션 그래프
- `<Feature>Screen.kt` - UI 화면 (Composable)
- `<Feature>ViewModel.kt` - 상태 관리
- `<Feature>State.kt` - 상태 데이터 클래스

## Repositories
- Android: https://github.com/ConJam-project/ConJam
- Backend: https://github.com/ConJam-project/ConJam_Backend
