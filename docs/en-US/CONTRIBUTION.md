# Contributing to obodc

This repository is the fork-owned mainline at [Minorli/obodc](https://github.com/Minorli/obodc).
If you want to contribute code, report issues, or discuss product direction, use the fork by default.

## Where to Start

- Issues: [Minorli/obodc/issues](https://github.com/Minorli/obodc/issues)
- Discussions: [Minorli/obodc/discussions](https://github.com/Minorli/obodc/discussions)
- Pull requests: [Minorli/obodc/pulls](https://github.com/Minorli/obodc/pulls)
- Developer guide: [DEVELOPER_GUIDE.md](./DEVELOPER_GUIDE.md)

If your topic is specifically about upstream tracking, compatibility, or selective cherry-picks from the upstream repository,
document that context explicitly in the issue or PR.

## Code of Conduct

This project follows the repository [Code of Conduct](../../CODE_OF_CONDUCT.md).
If you encounter unacceptable behavior, contact the repository maintainers through GitHub issues or discussions.

## Repository Structure

```text
apps/           Canonical home for runnable products and entry modules
packages/       Canonical home for shared backend modules and local libraries
extensions/     Canonical home for plugins, starters, and modules
tests/          Canonical home for integration and repository-level test assets
tools/          Canonical home for developer and packaging scripts
build/          Canonical home for build config and packaging assets
third_party/    Canonical home for vendored source and local binaries
client/         Frontend submodule location; aliased by apps/web-client
distribution/   Compatibility alias for build/packaging and build/output
script/         Compatibility alias for tools/scripts
server/         Compatibility aliases for moved backend modules
openspec/       Fork-owned specs and change proposals
```

## Before You Open a PR

- Make sure the change belongs in this fork rather than upstream.
- Open or link an issue first.
- Describe whether the change is:
  - fork-only product work
  - a backport/cherry-pick from upstream
  - a bug fix found while diverging from upstream
- Run the relevant build and test steps before requesting review.
- Keep commits readable and scoped.

## PR Title Format

Use conventional commit style:

```text
<type>[optional scope]: <description>
```

Examples:

```text
feat(metadata): cap oversized metadata browse payloads
fix(sql): avoid blocking metadata refresh on request path
docs(branding): rebrand root surfaces to obodc
```

## Bug Report Template

```markdown
[Problem Overview]

[Environment]

- obodc version:
- Database version:
- Operating system:
- Deployment mode:

[Reproduction Steps]

[Expected Result]

[Actual Result]

[Related Logs / Screenshots]
```

## Review Expectations

- Prefer focused PRs over broad mixed changes.
- Explain operational risk for behavior changes.
- Call out any inherited upstream context when relevant.
- Add or update docs when user-visible behavior changes.

## Inherited History

This repository preserves upstream ODC history, but new contributions should target the fork workflow and naming.
