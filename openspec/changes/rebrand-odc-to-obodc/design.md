## Context
This repository started as a fork of ODC and now needs a clean public identity. The highest-value cleanup is not deep
Java package renaming; it is the visible surface that users, operators, and contributors see first:

- README
- docs
- Maven metadata
- Docker labels
- release version markers

## Goals / Non-Goals

### Goals
- Make `obodc` the visible product name at the repository root
- Make contributor docs point at the fork instead of upstream by default
- Retain explicit upstream references only in upstream-tracking documentation and inherited-history notes

### Non-Goals
- Package namespace migration
- Artifact coordinate migration away from `com.oceanbase`
- Full rewrite of all historical release notes

## Decisions

### Decision: Rebrand visible surfaces first
We will prioritize root and docs-facing strings over internal identifiers.

Why:
- users and contributors experience those first
- this avoids destabilizing the build or runtime in phase 1

### Decision: Preserve inherited history but relabel it
Inherited changelog content remains, but the top section will clearly state that pre-fork history comes from upstream ODC.

Why:
- keeps useful release context
- avoids pretending the fork wrote the full upstream history

### Decision: Keep upstream links only where operationally required
Most documentation should point to `Minorli/obodc`; only upstream tracking docs should default to `oceanbase/odc`.

## Risks / Trade-offs
- Some internal names like `odc-server` will remain temporarily, which may look inconsistent.
  - Mitigation: track deeper runtime/package renaming in a later change.
- Old changelog entries still mention ODC.
  - Mitigation: explicitly label them as inherited history.
