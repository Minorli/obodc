## ADDED Requirements

### Requirement: Repository Layout Must Be Role-Based
The repository SHALL expose a canonical top-level layout grouped by role rather than by inherited historical placement.

#### Scenario: Contributor inspects repository root
- **WHEN** a contributor opens the repository root
- **THEN** the top-level directories SHALL separate applications, packages, extensions, tests, build assets, tools, and third-party content

### Requirement: Migration Must Preserve Buildability Through Compatibility Wrappers
The repository migration SHALL provide compatibility wrappers for legacy entrypoints during the transition.

#### Scenario: Developer runs legacy script path during migration
- **WHEN** a developer invokes a legacy script entrypoint such as `script/...`
- **THEN** the invocation SHALL continue to work by forwarding to the canonical new path until wrappers are explicitly removed

### Requirement: Packaging And CI Must Use Canonical Paths
Build scripts, Maven packaging, and CI workflows SHALL resolve source-owned assets from canonical repository locations.

#### Scenario: Packaging job collects artifacts
- **WHEN** a packaging or CI job runs after the migration phase for its area
- **THEN** it SHALL use the canonical path for those assets rather than relying on the old inherited path

### Requirement: Generated Outputs Must Be Separated From Source-Owned Packaging Definitions
Generated packaging outputs SHALL be stored separately from source-owned packaging definitions.

#### Scenario: Developer builds artifacts locally
- **WHEN** a developer runs a packaging or build flow
- **THEN** generated artifacts SHALL land in designated output locations rather than mixing with source-owned packaging definitions
