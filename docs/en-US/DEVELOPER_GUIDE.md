# obodc Backend Developer Guide

This guide covers the backend development workflow for the fork-owned `obodc` mainline.
Use this repository by default:

- Repository: [https://github.com/Minorli/obodc](https://github.com/Minorli/obodc)
- Default branch: `main`
- Current version line: `0.1.0-SNAPSHOT`

## 1. Clone the Repository

```bash
git clone git@github.com:Minorli/obodc.git
cd obodc
```

## 2. Toolchain

- JDK 8
- Maven Wrapper from the repository root
- Node.js only if you need frontend work
- IntelliJ IDEA recommended for Java development

## 3. Build Dependencies

The project still relies on a small number of locally installed binary dependencies stored under `import/`.
Install them first:

```bash
tools/scripts/build_libs.sh
```

If you need frontend assets locally:

```bash
tools/scripts/init_node_env.sh
tools/scripts/update_submodule.sh
tools/scripts/build_sqlconsole.sh
```

## 4. Backend Build

```bash
./mvnw clean package
```

For a faster inner loop:

```bash
./mvnw -pl server/odc-service -am test
```

## 5. Local Runtime

Set MetaDB connection settings before startup:

```bash
export DATABASE_HOST=127.0.0.1
export DATABASE_PORT=2881
export DATABASE_NAME=odc_metadb
export DATABASE_USERNAME=odc@test
export DATABASE_PASSWORD='your_password'
export ODC_SERVER_PORT=8989
```

Start the backend:

```bash
tools/scripts/nohup-start-odc.sh
```

## 6. Tests

Some tests depend on real databases. Local secrets should live outside version control.

- Create `local-unit-test.properties` in the repository root if needed.
- Keep decryption keys in local environment variables or `.env`.
- Do not commit local secrets or plaintext credentials.

Typical commands:

```bash
./mvnw test
./mvnw -pl server/odc-service -am -Dtest=SomeTest test
```

## 7. Formatting and IDE

- The repository formatting rules currently live under `builds/code-style/` and are planned to migrate under `build/`.
- IntelliJ IDEA project settings under `.idea/codeStyle` should be used as the baseline.
- Install Lombok support if your IDE needs it.

## 8. Fork Workflow

This fork selectively cherry-picks useful upstream changes, but new work should target the fork first.

- Track upstream work in [docs/UPSTREAM_TRACKING.md](../../docs/UPSTREAM_TRACKING.md)
- Put major product or architecture changes under `openspec/`
- Keep runtime-compatible identifiers unless a later change explicitly migrates them

## 9. Packaging

Packaging notes live in [distribution/README.md](../../distribution/README.md).
At the current `0.1.0-SNAPSHOT` stage, build artifacts are expected to come from source builds in this repository.
