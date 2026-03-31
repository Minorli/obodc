#!/usr/bin/env bash
# Legacy compatibility wrapper. Canonical path: tools/scripts/build_rpm.sh

legacy_dir=$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)
repo_root=$(cd "${legacy_dir}/.." && pwd)
exec "${repo_root}/tools/scripts/build_rpm.sh" "$@"
