# Modernization Plan

A learning-first plan to bring this project's practices up to date (the dependency
stack itself — Spring Boot 4.1, Kotlin 2.4, Gradle 8.14, Java 21 — is already current).
Each phase is independently shippable and ordered by dependency.

## Phase 1 — Build foundation: Gradle 9 + version catalog + CI

The biggest gap between this repo and current idiom is the build setup. Gradle 9
(9.0 mid-2025, latest 9.8.x) carried all breaking changes in 9.0; later minors only
add features. It requires Java 17+ to run, removes everything deprecated in
Gradle 8, embeds Kotlin 2 (K2) for Kotlin DSL, and makes the configuration cache
the headline feature.

1. **Deprecation sweep on 8.14.4**: run `./gradlew build --warning-mode all` and
   fix every warning (the official Gradle 9 upgrade path).
2. **Bump the wrapper to 9.8.x**: `./gradlew wrapper --gradle-version 9.8`, then
   run the full build.
3. **Enable the configuration cache**: add
   `org.gradle.configuration-cache=true` to `gradle.properties` and fix flagged
   issues — notably the `subprojects {}` cross-project configuration in
   `build.gradle.kts`.
4. **Adopt a version catalog**: move `buildSrc/src/main/kotlin/Versions.kt`
   constants to `gradle/libs.versions.toml`, and drop the
   `io.spring.dependency-management` plugin.
5. **Add convention plugins** to replace the `subprojects {}` block, written
   configuration-cache-clean from the start.
6. **Add CI**: GitHub Actions workflow running `./gradlew build` on push and PR.

## Phase 2 — API layer

- Replace `ResponseValidator` / `@ResponseStatus` exceptions with RFC 9457
  `ProblemDetail` responses produced in an `@ControllerAdvice`.
- Remove `server.error.include-message=always` once errors are structured.

## Phase 3 — Runtime practices

- Enable virtual threads: `spring.threads.virtual.enabled=true`.
- Add Actuator + Micrometer and structured logging; see what Boot 4 provides
  out of the box.
- Retire the Heroku `Procfile`: ship via `./gradlew bootBuildImage` (Buildpacks)
  or a layered-jar Dockerfile.

## Phase 4 — Testing depth

- Move the tests from the root `src/test` into their modules
  (MockMvc tests with `api`, service tests with `service`).
- Add one Testcontainers-based integration test against Postgres as a
  learning exercise.

## Phase 5 — Optional stretch

- GraalVM native image via Buildpacks.
- Domain-in-the-middle refactor (pure-Kotlin `modules/domain` with ports and
  adapters) — only if the Gradle module-structure practice is wanted; the
  architecture itself is already familiar.
