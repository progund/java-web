#!/bin/bash

#cat | jq 'if type == "array" then .[] else . end' | jq -c 'path(..)' | tr -d '[\[\]"]' | tr ',' '.' | sed -e 's/\(^[a-z_.-]*\)\(\.[0-9]\+\)\(.*\)/.\1[\2\]\3/g'|sed -e 's/\.\[/[/g' | sed -e 's/\[\./[/g' | sed -e 's/\(^[^.]\)/.\1/g' | sed -e 's/\([a-z]\+-[a-z]\+\)/"\1"/g'
cat | jq 'if type == "array" then .[] else . end' | jq -c 'path(..)' | tr -d '[\[\]"]' | tr ',' '.' | sed -e 's/\(^[a-z_.-]*\)\(\.[0-9]\+\)\(.*\)/.\1[\2\]\3/g;s/\.\[/[/g;s/\[\./[/g;s/\(^[^.]\)/.\1/g;s/\([a-z]\+-[a-z]\+\)/"\1"/g'

