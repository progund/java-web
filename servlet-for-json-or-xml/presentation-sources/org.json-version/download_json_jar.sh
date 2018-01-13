#!/bin/bash

echo "Downloading Json-lib (Apache License) from maven..."
wget 'https://search.maven.org/remotecontent?filepath=org/json/json/20171018/json-20171018.jar' -O webroot/WEB-INF/lib/org.json.jar
