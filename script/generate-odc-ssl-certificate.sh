#!/usr/bin/env bash
# Legacy compatibility wrapper. Canonical path: tools/scripts/generate-odc-ssl-certificate.sh

legacy_dir=$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)
repo_root=$(cd "${legacy_dir}/.." && pwd)
exec "${repo_root}/tools/scripts/generate-odc-ssl-certificate.sh" "$@"
