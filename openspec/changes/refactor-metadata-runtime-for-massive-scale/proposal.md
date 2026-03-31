# Change: Refactor Metadata Runtime For Massive Scale

## Why
Current ODC metadata behavior assumes relatively small deployments and mixes synchronous browsing, request-path refresh,
large result sets, and broad background synchronization. In production environments with hundreds of data sources and
very large schemas, this leads to:

- browser lockups due to full list responses
- multi-GB response payloads
- metadb overload
- request latency spikes caused by synchronous metadata refresh
- manual "sync all" operations that can self-denial-of-service the platform

This change establishes a first-class large-scale metadata runtime model.

## What Changes
- Add a dedicated `odc.database.metadata-runtime.*` configuration surface for large-scale metadata controls.
- Enforce hard result caps for metadata browsing APIs that currently return unbounded lists.
- Disable request-path metadata synchronization by default for the new large-scale runtime path.
- Make manual bulk metadata sync quota-aware and reject unsafe sync requests.
- Bound background datasource/database schema synchronization so that periodic jobs cannot fan out unbounded work.
- Reduce risky default pagination behavior on datasource/database list APIs.

## Impact
- Affected specs:
  - `metadata-runtime`
- Affected code:
  - metadata browsing controllers and services
  - background metadata sync schedulers and task managers
  - datasource/database listing controllers
  - v1 session-based object list services

## Non-Goals
- Full frontend redesign for tree pagination in this change
- Complete removal of the existing metadb-backed metadata index model
- A distributed metadata service split-out
- Reworking every list API in the entire product

## Rollout
- Phase 1: hard guards, caps, and bulk-sync protection
- Phase 2: explicit search-first APIs and frontend adoption
- Phase 3: deeper incremental sync and storage/index improvements
