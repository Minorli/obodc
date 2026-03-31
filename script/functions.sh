#!/usr/bin/env bash
# Legacy compatibility wrapper. Canonical path: tools/scripts/functions.sh

legacy_dir=$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)
repo_root=$(cd "${legacy_dir}/.." && pwd)
# shellcheck source=/dev/null
source "${repo_root}/tools/scripts/functions.sh"
