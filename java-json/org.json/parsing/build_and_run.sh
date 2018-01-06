#!/bin/bash

PATHSEP=":"
if [[ $OS == "Windows_NT" ]] || [[ $OSTYPE == "cygwin" ]]
then
    PATHSEP=";"
fi

JAVA_FILES="jsonexample/*/*.java"
MAIN_CLASS="jsonexample.main.ParseSchedule"
ORG_JSON="org.json.jar"
CP=".${PATHSEP}${ORG_JSON}"
javac -cp "${CP}" ${JAVA_FILES} && java -cp "${CP}" ${MAIN_CLASS}
