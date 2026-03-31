# Project Context

## Purpose
`obodc` is a performance-hardened derivative of OceanBase Developer Center (ODC) focused on making metadata browsing,
session management, and collaborative database operations robust under very large deployment scales.

The primary business goal of this fork is to support production environments with:

- hundreds to low-thousands of data sources
- large OceanBase tenants and schemas
- tens or hundreds of thousands of objects per data source
- multi-user concurrent access without browser lockups or metadb collapse

## Tech Stack
- Java 8
- Spring Boot
- Spring MVC / Spring Security
- Spring Data JPA
- MyBatis
- PF4J plugin architecture
- Druid / Hikari data sources
- OceanBase JDBC
- Maven multi-module build
- Docker / RPM deployment

## Project Conventions

### Code Style
- Prefer focused classes with explicit responsibilities over large grab-bag services.
- Keep changes ASCII-only unless the file already requires Unicode.
- Prefer immutable local flow where practical, but preserve the surrounding project style in existing Java services.
- Use clear configuration property names under `odc.database.metadata-runtime.*` for new large-scale runtime controls.
- Any high-risk runtime behavior should be behind explicit configuration flags with safe defaults.

### Architecture Patterns
- ODC is a Spring Boot monolith with dynamic plugin loading via PF4J.
- Request-path behavior must be lightweight; heavy synchronization belongs to background executors.
- Metadata browsing must be search-first and quota-aware at large scale.
- Background synchronization must be bounded, rate-limited, and never assume the deployment is small.
- Manual "sync all visible metadata" operations must be guarded to prevent self-DoS.

### Testing Strategy
- Prefer targeted unit tests for new guards, limits, and helper utilities.
- For service behavior changes, add focused tests where the project already has unit or integration coverage nearby.
- At minimum, run compile-level verification for touched modules plus targeted tests for new utility code.

### Git Workflow
- Use small, reviewable commits.
- Keep spec changes and code changes in the same branch while iterating locally.
- Do not archive OpenSpec changes until the implementation is accepted and production-validated.

## Domain Context
ODC serves both interactive SQL-console style access and centralized metadata, permission, and workflow management.

This creates a tension:

- client-style tools like PL/SQL Developer are strong because they are lazy, local, and session-centric
- ODC is weaker at scale when it behaves like a centralized crawler and tries to eagerly synchronize everything

This fork prioritizes the PL/SQL-Developer-like properties for metadata browsing:

- lazy loading
- bounded payloads
- no request-path synchronization storms
- explicit search and drill-down rather than full-tree expansion

## Important Constraints
- The public repository is behind some internal release lines, so changes should be robust even if production packaging differs.
- Existing APIs are already used by frontend code, so breaking response shapes should be minimized in the first phase.
- Docker deployment is common; runtime overrides via env vars and startup scripts matter.
- The metadb is a shared bottleneck and must be protected from full-table scans and large result sets.
- Team-mode authorization semantics still matter; we cannot solve scale by blindly bypassing all permission-aware paths.

## External Dependencies
- OceanBase MySQL tenant for metadb
- OceanBase/MySQL/Oracle/Postgres/Doris target data sources through plugins
- PF4J plugin jars in `plugins/`
- starter jars in `starters/`
- Docker supervisor startup chain using `/opt/odc/bin/start-odc.sh`
