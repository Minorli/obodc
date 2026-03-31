# Upstream Tracking

## Purpose

This repository is now the fork-owned mainline for `obodc`.

Upstream `oceanbase/odc` remains the source of:

- security and dependency updates
- correctness fixes that are easy to merge
- feature work worth selectively adopting

But upstream is no longer treated as the canonical product roadmap for this fork.

## Git Remotes

- `origin`: `git@github.com:Minorli/obodc.git`
- `upstream`: `https://github.com/oceanbase/odc`

## Branch Policy

- `main`
  - fork-owned mainline
  - version line starts from `0.1.0-SNAPSHOT`
- upstream sync work
  - use short-lived topic branches
  - cherry-pick or merge only after local validation

## Upstream PR Triage

### Merged Into obodc

- `#4422`
  - `fix(flow): fix multiple database change subtasks do not link description information`
  - merged into `obodc/main`

- `#4164`
  - `fix(db-browser): the precision of a MySQL YEAR type can be 0`
  - merged into `obodc/main`

### Candidate To Cherry-Pick Early

- keep this section for new low-risk candidates after the current merged set

### Candidate To Evaluate Carefully

- `#4328`
  - `enhancement(task-framework): opt task result process`
  - Touches task result processing and terminate listeners
  - Worth reviewing because this fork cares about robustness under load
  - URL: `https://github.com/oceanbase/odc/pull/4328`

- `#4488`
  - `WIP(sqlInteceptor): support external sql interceptor call back`
  - Large feature and not obviously complete
  - Track, do not merge blindly
  - URL: `https://github.com/oceanbase/odc/pull/4488`

- `#4334`
  - `wip(osc): introduce odc online schema change adaptor`
  - Potentially large surface-area change
  - Track, do not merge blindly
  - URL: `https://github.com/oceanbase/odc/pull/4334`

### Security / Dependency Watchlist

These are not immediate merge candidates without a compatibility pass, but should be tracked:

- `#4503` log4j-core in `packages/libs/db-browser`
- `#4502` log4j-core root dependency
- `#4500` netty-codec-http
- `#4499` tika-core
- `#4498` jgit
- `#4497` tomcat-embed-core
- `#4496` bouncycastle
- `#4490` commons-lang3
- `#4453` commons-beanutils
- `#4451` spring-web
- `#4355` poi-ooxml

These require:

1. dependency compatibility review
2. JDK 8 / runtime baseline review
3. product regression testing

## Upstream Issue Watchlist

### Directly Relevant To This Fork

- `#3984`
  - optimize speed of entering the data source interface
- `#4011`
  - SQL window parsing performance optimization
- `#3682`
  - desktop startup speed optimization
- `#3062`
  - metadb max_allowed_packet requirement for startup
- `#4051`
  - monitor-related content as an independent starter
- `#4434`
  - unable to directly run stored procedures/packages and return result sets
- `#4378`
  - feature request for executing stored procedures and returning result sets in SQL window
- `#4253`
  - database_name missing in SQL console execution
- `#3874`
  - package sub-package names inconsistent with DDL

### Product-Level Signals

- `#4501`
  - complaint that the upstream project appears inactive
  - strengthens the need for this fork to own its release cadence

## Merge Rules

- Do merge:
  - low-risk correctness fixes
  - small isolated db-browser fixes
  - security updates after compatibility validation
- Do not merge blindly:
  - WIP features
  - large task framework rewrites
  - dependency jumps that imply framework baseline changes
  - UI/UX changes that conflict with fork product direction

## Operating Procedure

1. Review upstream open PRs weekly.
2. Reclassify PRs into:
   - cherry-pick now
   - watch
   - ignore
3. Review upstream open issues weekly for:
   - metadata/runtime performance
   - datasource/session robustness
   - UI/UX signals worth adopting
4. Record cherry-pick decisions in commit messages and release notes.
