## 1. OpenSpec
- [x] 1.1 Add `refactor-repository-layout-for-obodc` proposal
- [x] 1.2 Add repository layout design note
- [x] 1.3 Add repository layout spec delta

## 2. Phase 1 Skeleton
- [x] 2.1 Create canonical top-level directories for apps, packages, extensions, tests, tools, build, and third_party
- [x] 2.2 Add compatibility wrappers for legacy `script/` and selected packaging entrypoints
- [x] 2.3 Update root documentation to describe canonical layout

## 3. Phase 2 Build And Packaging Migration
- [x] 3.1 Move script sources to `tools/scripts/` and keep legacy wrappers
- [x] 3.2 Move source-owned packaging assets to `build/packaging/`
- [x] 3.3 Move local binary and vendored assets to `third_party/` and `build/resources/`
- [x] 3.4 Update Maven paths, packaging scripts, and CI workflows

## 4. Phase 3 Java Module Migration
- [x] 4.1 Move app entry modules under `apps/`
- [x] 4.2 Move shared backend modules under `packages/backend/`
- [x] 4.3 Move local reusable libraries under `packages/libs/`
- [x] 4.4 Move plugins, starters, and modules under `extensions/`
- [x] 4.5 Move integration tests and test scripts under `tests/`

## 5. Phase 4 Cleanup
- [ ] 5.1 Remove obsolete legacy wrappers after verification
- [ ] 5.2 Update `CODEOWNERS`, docs, and release tooling to canonical paths
- [ ] 5.3 Verify targeted builds and runtime startup paths after migration

## 6. Verification
- [x] 6.1 Run `openspec validate refactor-repository-layout-for-obodc --strict`
- [x] 6.2 Run targeted grep/path audit for legacy layout references
- [x] 6.3 Run representative Maven and packaging verification after each migration phase
