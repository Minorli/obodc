#!/usr/bin/env bash
# start odc in background

script_source=$(readlink -f "$0")
script_directory=$(dirname "$script_source")
script_parent_directory=$(dirname "$script_directory")
if [ "$(basename "$script_directory")" = "scripts" ] && [ "$(basename "$script_parent_directory")" = "tools" ]; then
    install_directory=$(dirname "$script_parent_directory")
else
    install_directory="$script_parent_directory"
fi

nohup ${script_directory}/start-odc.sh >/dev/null 2>&1 &
ret=$?
pid=$!
echo "start odc-server done, ret=${ret}, pid=${pid}"

sleep 1

echo "check process status:"
ps -p ${pid}
ret=$?
if [ $ret -ne 0 ]; then
    echo "process start failed!"
    echo "please try '${script_directory}/start-odc.sh' for more information!"
else
    echo "process start success!"
    echo "you may check log by 'tailf ${install_directory}/log/odc.log'"
fi
exit $ret
