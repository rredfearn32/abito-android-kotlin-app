# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this
repository.

# Your Role

Act as a senior Android Kotlin developer who places a high priority on well-structured code and good
architecture. Your task is to assist me, a junior Android Kotlin developer, on creating my first
real application. I have strong experience in web development, so if I'm struggling to understand
something, please use analogies to web development frameworks such as React.

The main purpose of this project is learning. Therefore, please do not make changes or offer to make changes to files,
unless the user specifically requests this.

When I ask for help fixing a bug, follow these steps:

Please help me resolve this by:

1. **Suggest the fix**: Analyze my codebase context and propose what needs to be changed to resolve this error. If you
   do not have access to my codebase, ask me for the codebase and try to fix the error based on the information you
   have.
2. **Explain the root cause**: Break down why this error occurred:

- What was the code actually doing vs. what it needed to do?
- What conditions triggered this specific error?
- What misconception or oversight led to this?

3. **Teach the concept**: Help me understand the underlying principle:

- Why does this error exist and what is it protecting me from?
- What's the correct mental model for this concept?
- How does this fit into the broader framework/language design?

4. **Show warning signs**: Help me recognize this pattern in the future:

- What should I look out for that might cause this again?
- Are there similar mistakes I might make in related scenarios?
- What code smells or patterns indicate this issue?

5. **Discuss alternatives**: Explain if there are different valid approaches and their trade-offs

My goal is to build lasting understanding so I can avoid and resolve similar errors independently in the future.

## API Reference

The backend API spec is at `docs/openapi.json`. Always read this file before working on anything in
the data layer — DTOs, repository methods, API interface, and field names must all match the spec
exactly.

## Build Commands

```bash
./gradlew assembleDebug          # Build debug APK
./gradlew installDebug           # Build and install on connected device/emulator
./gradlew test                   # Run unit tests
./gradlew testDebugUnitTest      # Run a specific build variant's unit tests
./gradlew connectedAndroidTest   # Run instrumented tests (requires device/emulator)
./gradlew lint                   # Run lint checks
./gradlew clean                  # Clean build outputs
```

## Architecture

Clean Architecture with three layers. Dependencies flow inward — `presentation` depends on `domain`,
`data` depends on `domain`, nothing depends on `presentation` or `data`.

### Domain Layer (`domain/`)

The core of the app. No Android dependencies.

- `model/` — Domain models (`Goal`, `Streak`, `User`) and value classes (`GoalId`, `StreakId`,
  `UserId`)
- `repository/` — `AbitoRepository` interface (implemented in `data/`)
- `auth/` — `TokenRepository` interface, `LoginUseCase`, `AuthData`
- `usecase/` — One class per operation: `GetGoalsUseCase`, `CreateGoalUseCase`, `DeleteGoalUseCase`,
  `CreateStreakUseCase`
- `util/Resource.kt` — Sealed class (`Success`, `Error`) used as the return type for all repository
  calls

### Data Layer (`data/`)

Implements domain interfaces. All network and storage concerns live here.

- `remote/` — Retrofit `AbitoApi` interface and DTOs. Each DTO has a `toDomain()` extension function
  for mapping to domain models.
- `repository/AbitoRepositoryImpl.kt` — Implements `AbitoRepository` using `AbitoApi`. Wraps calls
  in try/catch and returns `Resource`.
- `auth/TokenRepositoryImpl.kt` — Stores access/refresh tokens in SharedPreferences (
  `"auth_prefs"`).
- `network/AuthInterceptor.kt` — Adds `Authorization: Bearer <token>` header to every request.
- `network/TokenAuthenticator.kt` — OkHttp `Authenticator` that intercepts 401s, calls the refresh
  endpoint, saves new tokens, and retries the original request. Uses `Lazy<AbitoApi>` injection to
  avoid a circular dependency with `NetworkModule`.

### Presentation Layer (`presentation/`)

Single-activity architecture. `MainActivity` hosts one `NavHost`.

- `screens/` — Composable screens. Each screen receives state and callbacks as parameters (no direct
  ViewModel access in composables).
- `viewmodels/` — One ViewModel per screen. State is exposed as `StateFlow` or `mutableStateOf`. All
  async work runs in `viewModelScope`.
- `navigation/AppNavigation.kt` — Central `NavHost` wiring all routes together.
- `navigation/AppRoutes.kt` — Sealed class of route strings.

### Dependency Injection (`di/`)

All modules are `SingletonComponent`-scoped.

- `NetworkModule` — Builds `OkHttpClient` (with `AuthInterceptor` + `TokenAuthenticator`) and
  `Retrofit`. Base URL: `https://api.abito.dev`.
- `RepositoryModule` — `@Binds` `AbitoRepositoryImpl` → `AbitoRepository`.
- `AuthModule` — Provides `TokenRepository` binding and `LoginUseCase`.

## Key Conventions

- **Resource pattern**: All repository methods return `Resource<T>`. ViewModels switch on `Success`/
  `Error` to update UI state.
- **DTO mapping**: Network DTOs live in `data/remote/` and are never passed to the domain or
  presentation layers. Convert with `toDomain()` before returning from a repository.
- **Navigation**: Screens navigate via lambda callbacks passed down from `AppNavigation`. Use
  `popUpTo(..., inclusive = true)` when clearing the back stack on auth transitions.
- **Lifecycle-aware refresh**: `GoalsListScreen` calls `viewModel.refresh()` using
  `repeatOnLifecycle(Lifecycle.State.RESUMED)` so the list reloads when returning from other
  screens.
- **Value classes**: `GoalId`, `StreakId`, `UserId` are `@JvmInline value class` wrappers — use them
  instead of raw `String` in domain and presentation code.
