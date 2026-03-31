# apps

Canonical home for runnable products and primary application entry modules.

Current state:

- `apps/server` now owns the backend runtime module, with `server/odc-server` kept as a compatibility symlink
- `apps/web-client` is the canonical app entry for the frontend and currently aliases the `client` submodule path
