## 1. OpenSpec
- [x] 1.1 Fill `openspec/project.md` with ODC large-scale runtime context
- [x] 1.2 Add `refactor-metadata-runtime-for-massive-scale` proposal
- [x] 1.3 Add metadata runtime design document
- [x] 1.4 Add spec delta for metadata runtime behavior

## 2. Runtime Controls
- [x] 2.1 Add a new metadata runtime configuration bean
- [x] 2.2 Lower dangerous datasource/database default page sizes
- [x] 2.3 Enforce hard browse caps for metadata list endpoints

## 3. Request-Path Protection
- [x] 3.1 Stop request-path table browsing from auto-triggering metadata sync
- [x] 3.2 Guard dangerous manual bulk sync endpoints with explicit quotas

## 4. Background Sync Protection
- [x] 4.1 Bound scheduled datasource sync fan-out per cycle
- [x] 4.2 Bound per-datasource database sync fan-out per cycle

## 5. Verification
- [x] 5.1 Add targeted tests for new limit/guard utility behavior
- [ ] 5.2 Run compile-level verification for touched modules
- [x] 5.3 Update task statuses to complete after verification
