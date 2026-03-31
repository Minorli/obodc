# Change: Rebrand ODC To obodc

## Why
This fork is now an independent mainline owned by `Minorli/obodc`. The public-facing documentation, product naming,
and release metadata still expose large amounts of inherited ODC branding and official repository references, which is
confusing for users and contributors.

## What Changes
- Rebrand top-level product-facing docs from ODC to obodc where appropriate.
- Update repository references from `oceanbase/odc` to `Minorli/obodc` in fork-owned documentation.
- Change top-level project metadata and Docker labels to obodc branding.
- Introduce a fork-specific product positioning section in README files.
- Keep historical upstream references only where they are explicitly needed for inherited changelog or upstream tracking.

## Impact
- Affected specs:
  - `branding`
- Affected code:
  - top-level README files
  - contribution/developer guides
  - root Maven metadata
  - Dockerfile labels and packaging descriptions

## Non-Goals
- Renaming Java package names from `com.oceanbase.odc`
- Renaming all inherited internal identifiers such as `odc-server`, environment variables, or old migration class names in this phase
- Rewriting the full inherited historical changelog body
