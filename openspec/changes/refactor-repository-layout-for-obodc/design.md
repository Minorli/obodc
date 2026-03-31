## Context
The fork now has a different product direction from upstream, but it still inherits upstream build assumptions deeply:

- top-level `script/` is hard-wired into packaging and dev flows
- `distribution/` doubles as both canonical packaging source and generated output sink
- `server/` mixes runtime apps, backend packages, plugin ecosystems, and test infrastructure
- `import/` and `build-resource/` contain local binary/runtime assets without clear ownership
- workflows, tests, and runtime defaults resolve many of these paths directly

This makes the repository hard to reason about and raises the cost of every future structural change.

## Goals / Non-Goals

### Goals
- Introduce a clearer role-based filesystem layout
- Reduce top-level cognitive load for contributors
- Separate app/runtime code from reusable packages and extension ecosystems
- Isolate generated packaging outputs from source-controlled build assets
- Preserve buildability through staged migration and wrappers

### Non-Goals
- Package namespace migration
- Artifact coordinate renaming
- Runtime behavior redesign beyond path compatibility

## Decisions

### Decision: Use role-based top-level grouping
Canonical top-level targets:

- `apps/` for runnable products and primary application entrypoints
- `packages/` for shared backend Java packages and local reusable libraries
- `extensions/` for plugins, starters, and dynamically loaded modules
- `tests/` for integration tests and reusable test scripts/support
- `tools/` for developer and packaging scripts
- `build/` for build config, packaging sources, and generated packaging outputs
- `third_party/` for vendored source and local binary dependencies

Why:
- aligns repository shape with system responsibilities
- makes future product work easier to locate and evolve

### Decision: Keep compatibility wrappers during migration
Legacy paths such as `script/`, `distribution/`, and selected old module paths will remain as wrappers or forwarding shells during transition.

Why:
- avoids a big-bang break across scripts, docs, CI, and runtime assumptions
- allows incremental path migration with bounded risk

### Decision: Preserve Maven and Java identities in phase 1
Filesystem relocation will not rename:

- Java package names
- Maven `groupId` / `artifactId`
- runtime installation names like `odc-server`

Why:
- separates repository hygiene from product/runtime compatibility
- reduces risk while the fork is still stabilizing

### Decision: Move generated outputs away from source-owned packaging descriptions
Generated packaging outputs should live under a dedicated generated/output subtree instead of sharing directories with source-owned packaging definitions.

Why:
- reduces accidental dirty worktrees
- makes packaging flow more legible

## Proposed Canonical Layout

```text
apps/
  server/                current server/odc-server
  web-client/            current client

packages/
  backend/
    common/              current server/odc-common
    core/                current server/odc-core
    service/             current server/odc-service
    migrate/             current server/odc-migrate
    test-support/        current server/odc-test
  libs/
    db-browser/          current libs/db-browser
    ob-sql-parser/       current libs/ob-sql-parser

extensions/
  plugins/               current server/plugins
  starters/              current server/starters
  modules/               current server/modules

tests/
  integration/           current server/integration-test
  scripts/               current server/test-script

tools/
  scripts/               current script

build/
  config/                current builds
  resources/             current build-resource
  packaging/             source-owned packaging assets from current distribution
  output/                generated jars/rpms/plugin bundles

third_party/
  source/                current server/3rd-party
  binaries/              current import
```

## Migration Plan

### Phase 1: Canonical skeleton and wrappers
- Create canonical directories
- Move low-risk source-owned assets first
- Leave wrappers at legacy entrypoints
- Update docs to describe canonical layout

### Phase 2: Build and packaging path migration
- Move `script/` to `tools/scripts/`
- Move `distribution/` source assets into `build/packaging/`
- Move `build-resource/` and `import/` under `build/` / `third_party/`
- Update scripts, POMs, and workflows

### Phase 3: Java module relocation
- Move backend packages under `packages/backend/`
- Move runtime app module under `apps/server/`
- Move extension ecosystems under `extensions/`
- Move tests under `tests/`

### Phase 4: Cleanup
- Remove compatibility wrappers once all path consumers are migrated
- Archive old-path assumptions from docs and workflows

## Risks / Trade-offs

- Large path churn will create merge friction with upstream
  - Mitigation: keep changes staged and isolate wrapper commits
- Packaging and runtime scripts may silently break if path audit is incomplete
  - Mitigation: update path consumers systematically and verify with targeted builds
- Generated output relocation can break developer expectations
  - Mitigation: document canonical output paths and provide wrapper scripts

## Open Questions
- Whether to keep `client/` as a special-case top-level entry during the first phase
- Whether `build/output/` should immediately replace `distribution/jar` artifacts or only after packaging verification
