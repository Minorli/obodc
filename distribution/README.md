# obodc Packaging

This directory contains the packaging assets for the fork-owned `obodc` mainline.

## Layout

```text
docker/      Docker build context and helper scripts
plugins/     Packaged plugin jars
starters/    Packaged starter jars
modules/     Packaged module jars
README.md    Packaging notes
```

## Current State

`0.1.0-SNAPSHOT` is the fork bootstrap stage.
Packaging remains source-oriented and inherits parts of the upstream structure for compatibility, but the published
identity is now `obodc`.

## Common Tasks

### Build backend packages

Use the repository root Maven Wrapper:

```bash
./mvnw clean package
```

### Build Docker assets

Use the helper scripts under `distribution/docker/`.

### Build RPM assets

RPM packaging is still driven by the backend Maven configuration and the helper scripts in `script/`.

## Notes

- Some runtime/package names still retain inherited `odc-*` identifiers for compatibility.
- Visible packaging descriptions and labels should use `obodc`.
- Deeper runtime renaming is tracked as a later fork change, not in this bootstrap phase.
