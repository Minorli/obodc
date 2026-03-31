# Change: Refactor Repository Layout For obodc

## Why
The current repository layout is inherited from upstream and mixes multiple concerns at the top level:
runtime applications, shared backend packages, plugin ecosystems, packaging assets, helper scripts,
binary imports, and third-party source code all sit beside one another.

That layout was workable for incremental upstream development, but it is a poor base for a fork that now intends to:

- evolve UI and product surface aggressively
- keep backend, packaging, and extension boundaries clearer
- reduce onboarding friction for contributors
- support larger-scale refactors without every path change touching unrelated areas

## What Changes
- Introduce a role-based top-level layout for applications, backend packages, extensions, build assets, tools, tests, and third-party assets.
- Move filesystem locations without renaming Java package namespaces or Maven artifact identifiers in the same phase.
- Add a staged compatibility layer so existing scripts, build steps, and packaging continue to work during migration.
- Update Maven module paths, scripts, CI, packaging, docs, and runtime path defaults to use canonical new locations.
- Keep inherited compatibility shims only for a bounded migration window.

## Impact
- Affected specs:
  - `repository-layout`
- Affected code:
  - root `pom.xml`
  - `script/` and packaging scripts
  - `distribution/` assets and Docker/RPM build flow
  - `.github/` workflows and `CODEOWNERS`
  - `server/`, `libs/`, `import/`, `build-resource/`, and related path consumers

## Non-Goals
- Renaming Java packages such as `com.oceanbase.odc`
- Renaming Maven artifactIds in the first repository-layout phase
- Rewriting business logic purely for cosmetic module moves
- Removing all compatibility wrappers in the first migration step
