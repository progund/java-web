#!/bin/bash

for i in *.json
do
    for jqpath in $(cat "$i" | ./jq-paths.sh)
    do
        echo "Running $i | jq \"$jqpath\"" >> paths.log
        cat $i | jq "$jqpath" 2>> errors.log 1>> paths.log
    done
done
