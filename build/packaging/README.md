# obodc Packaging

This directory contains the canonical packaging source assets for the fork-owned `obodc` mainline.

## Layout

```text
docker/             Docker build context and helper scripts
rpm/                RPM packaging definitions
server-version.txt  Canonical packaged server version marker
README.md           Packaging notes
```

## Current State

`0.1.0-SNAPSHOT` is the fork bootstrap stage.
Source-owned packaging assets now live here, while generated artifacts are being moved into `build/output/`.
Legacy `distribution/` paths remain available as compatibility symlinks during migration.

## Common Tasks

### Build backend packages

Use the repository root Maven Wrapper:

```bash
./mvnw clean package
```

### Build Docker assets

Use the helper scripts under `build/packaging/docker/`.

### Build RPM assets

RPM packaging is still driven by the backend Maven configuration and the helper scripts in `tools/scripts/`.

## Notes

- Some runtime/package names still retain inherited `odc-*` identifiers for compatibility.
- Visible packaging descriptions and labels should use `obodc`.
- Deeper runtime renaming is tracked as a later fork change, not in this bootstrap phase.
