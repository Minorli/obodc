![obodc](docs/en-US/images/odc-head.png)

English | [中文](./README-zh-CN.md)

# obodc

`obodc` is the new mainline of this fork, maintained at [Minorli/obodc](https://github.com/Minorli/obodc).
It starts from the public upstream code line and is being rebuilt into a faster, more robust database development
workspace for large-scale OceanBase environments.

## Fork Status

- Current fork version: `0.1.0-SNAPSHOT`
- Default branch: `main`
- Upstream baseline: public upstream `v4.3.4_bp2`
- Current focus:
  - hardening metadata browsing for very large datasource estates
  - cleaning inherited branding and packaging surfaces
  - preparing a new UI/UE iteration on top of the fork

## What obodc Is Optimizing For

- Large OceanBase fleets with hundreds of datasources and massive schema counts
- Faster startup and lower MetaDB pressure
- Safer metadata browsing that does not explode browser payload size
- A cleaner product surface for future UI and feature work

## Repository Layout

```text
apps/           Canonical home for runnable products and entry modules
packages/       Canonical home for shared backend modules and local libraries
extensions/     Canonical home for plugins, starters, and modules
tests/          Canonical home for integration and repository-level test assets
tools/          Canonical home for developer and packaging scripts
build/          Canonical home for build config and packaging assets
third_party/    Canonical home for vendored source and local binaries
client/         Compatibility alias for the vendored frontend workspace
distribution/   Compatibility alias for build/packaging and build/output
script/         Compatibility alias for tools/scripts
server/         Compatibility aliases for moved backend modules
openspec/       Fork-owned specifications and change proposals
```

## Quick Start

### Clone

```bash
git clone git@github.com:Minorli/obodc.git
cd obodc
```

### Build local dependencies

```bash
tools/scripts/build_libs.sh
```

### Build backend

```bash
./mvnw clean package -DskipTests
```

### Run locally

Set the MetaDB environment variables first, then start the server:

```bash
export DATABASE_HOST=127.0.0.1
export DATABASE_PORT=2881
export DATABASE_NAME=odc_metadb
export DATABASE_USERNAME=odc@test
export DATABASE_PASSWORD='your_password'
export ODC_SERVER_PORT=8989

tools/scripts/nohup-start-odc.sh
```

## Packaging

`0.1.0-SNAPSHOT` is a fork bootstrap line. Official fork-owned container images and release artifacts are not published yet.
For now, build packages from source in this repository.

- Packaging overview: [build/packaging/README.md](build/packaging/README.md)
- Backend development guide: [docs/en-US/DEVELOPER_GUIDE.md](docs/en-US/DEVELOPER_GUIDE.md)
- Contribution guide: [docs/en-US/CONTRIBUTION.md](docs/en-US/CONTRIBUTION.md)
- Upstream tracking policy: [docs/UPSTREAM_TRACKING.md](docs/UPSTREAM_TRACKING.md)

## Roadmap

The first fork milestones are:

1. Stabilize large-scale metadata browsing and sync behavior.
2. Rework visible branding, UI, and interaction patterns.
3. Build a stronger SQL and operational workflow for OceanBase-heavy teams.
4. Continue selectively merging valuable upstream fixes while diverging on product direction.

## Contributing

Issues, pull requests, and discussions should go to the fork by default:

- Issues: [Minorli/obodc/issues](https://github.com/Minorli/obodc/issues)
- Discussions: [Minorli/obodc/discussions](https://github.com/Minorli/obodc/discussions)
- Pull requests: [Minorli/obodc/pulls](https://github.com/Minorli/obodc/pulls)

Read the contributor guide before sending changes:

- [docs/en-US/CONTRIBUTION.md](docs/en-US/CONTRIBUTION.md)
- [docs/en-US/DEVELOPER_GUIDE.md](docs/en-US/DEVELOPER_GUIDE.md)

## Inherited History

This fork preserves the upstream release history for reference. New fork work starts at `0.1.0-SNAPSHOT`.
Older changelog entries are inherited from upstream ODC and are kept for historical context only.

## License

This repository remains licensed under [Apache-2.0](https://www.apache.org/licenses/LICENSE-2.0).
