#!/bin/bash

cat | jq 'if type == "array" then .[] else . end' | jq -c 'path(..)' | tr -d '[\[\]"]' | tr ',' '.' | sed -e 's/\(^[a-z_.]*\)\([0-9]\+\)\(.*\)/.\1[\2\]\3/g'|sed -e 's/\.\[/[/g'

