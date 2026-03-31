## Context
ODC currently exhibits several anti-patterns under large-scale metadata workloads:

- metadata list endpoints return unbounded or extremely large pages
- request handlers may trigger metadata synchronization
- background jobs enumerate all syncable data sources or databases
- the system tries to behave like a centralized metadata crawler rather than a lazy database client

This is fundamentally different from tools like PL/SQL Developer, which remain responsive by loading only the current
scope and avoiding platform-wide refresh on ordinary browse operations.

## Goals / Non-Goals

### Goals
- Protect ODC and the browser from multi-GB metadata responses
- Ensure browsing a datasource or database does not implicitly start heavy synchronization
- Prevent manual and scheduled bulk sync from overwhelming the platform
- Preserve compatibility as much as possible for existing clients
- Introduce explicit configuration flags for large-scale deployments

### Non-Goals
- Full UI protocol redesign
- Perfect metadata freshness in the first phase
- Eliminating all stale metadata scenarios

## Decisions

### Decision: Introduce a large-scale metadata runtime configuration
Create a dedicated configuration bean under `odc.database.metadata-runtime.*` to centralize:

- max browse items per request
- max legacy list items per request
- max datasource/database page size
- whether request-path sync is allowed
- whether bulk visible-database sync is allowed
- max auto-sync datasource fan-out per cycle
- max per-datasource database fan-out per cycle
- max manual sync database fan-out

Why:
- avoids scattering hardcoded limits across services
- makes operations tunable without code recompilation

### Decision: Use hard caps instead of "best effort" pagination
For legacy and current browse endpoints that still return `List<T>`, apply server-side truncation with deterministic
ordering.

Why:
- frontend may not yet pass pagination parameters
- hard caps are the fastest way to stop catastrophic payloads

Trade-off:
- users may not see every object in a single browse response
- acceptable because the alternative is browser/process failure

### Decision: Remove request-path metadata synchronization from browse operations
When cached metadata does not match live database state, browse requests must not trigger synchronization by default.

Why:
- browsing must be cheap and predictable
- heavy reconciliation belongs to background jobs or explicit sync actions

Trade-off:
- stale metadata may remain visible longer
- this is preferable to synchronous system-wide stalls

### Decision: Reject unsafe manual bulk sync
Manual sync operations targeting too many databases must fail fast with a clear error.

Why:
- "sync all visible databases" at large scale is an operator footgun
- guardrails are required for production resilience

### Decision: Bound background fan-out
Scheduled jobs must cap how many data sources and databases they submit in a single cycle.

Why:
- prevents scheduler storms after startup or on each interval
- spreads work across cycles instead of collapsing the cluster into one burst

## Risks / Trade-offs
- Some existing UX flows may now return partial lists where they previously attempted full lists.
  - Mitigation: deterministic limits, clear logging, and future frontend pagination work.
- Operators may perceive stale metadata more often.
  - Mitigation: explicit sync controls and safe manual targeted refresh.
- Some tests may assume uncapped list sizes.
  - Mitigation: update tests to assert bounded behavior where relevant.

## Migration Plan
1. Add metadata runtime configuration bean with safe defaults.
2. Apply hard caps to the most dangerous browse/list endpoints.
3. Disable request-path sync in browse flows by default.
4. Add manual bulk-sync safety checks.
5. Add scheduled fan-out caps.
6. Validate compile and targeted tests.

## Open Questions
- Whether the frontend should receive an explicit `truncated` signal in all list APIs in phase 2.
- Whether team-mode table browsing should eventually use a dedicated live-browse API separate from metadb-index paths.
