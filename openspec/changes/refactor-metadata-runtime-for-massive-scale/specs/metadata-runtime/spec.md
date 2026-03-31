## ADDED Requirements

### Requirement: Metadata Browse Responses Must Be Bounded
The system SHALL enforce deterministic server-side result caps for metadata browse endpoints that would otherwise return
unbounded object lists.

#### Scenario: Cap current-database object browse results
- **WHEN** a user requests tables, views, procedures, functions, sequences, triggers, packages, synonyms, or types for a database
- **THEN** the server SHALL return at most the configured maximum number of items for that request

#### Scenario: Cap datasource and database list page sizes
- **WHEN** a client omits page size or requests an overly large page for datasource or database listing APIs
- **THEN** the server SHALL apply the configured safe upper bound instead of returning effectively unbounded pages

### Requirement: Browse Requests Must Not Trigger Heavy Metadata Synchronization
The system SHALL prevent ordinary metadata browse requests from implicitly triggering large synchronization work by default.

#### Scenario: Cached metadata differs from live database state
- **WHEN** a browse request detects that cached metadata and live metadata are inconsistent
- **THEN** the server SHALL avoid synchronous metadata synchronization during the request unless explicitly enabled by configuration

### Requirement: Manual Bulk Metadata Sync Must Be Safety-Gated
The system SHALL reject manual metadata synchronization requests that exceed configured safe fan-out thresholds.

#### Scenario: User requests sync for too many visible databases
- **WHEN** a manual sync request targets more than the configured maximum number of databases
- **THEN** the server SHALL reject the request with a clear validation error instead of starting the sync

#### Scenario: Datasource-level sync would fan out to too many databases
- **WHEN** a manual datasource sync resolves to more than the configured maximum number of databases
- **THEN** the server SHALL reject the request with a clear validation error instead of starting the sync

### Requirement: Scheduled Metadata Sync Must Be Bounded Per Cycle
The system SHALL limit scheduled metadata synchronization fan-out per cycle.

#### Scenario: Too many syncable datasources exist
- **WHEN** the periodic datasource sync job finds more syncable datasources than the configured per-cycle quota
- **THEN** the scheduler SHALL submit only up to the configured quota for that cycle

#### Scenario: Datasource contains too many databases for schema sync
- **WHEN** background schema sync expands a datasource into more databases than the configured per-datasource quota
- **THEN** the scheduler SHALL submit only up to the configured quota for that datasource in that cycle
