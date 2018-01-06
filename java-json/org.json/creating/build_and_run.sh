#!/bin/bash

PATHSEP=":"
if [[ $OS == "Windows_NT" ]] || [[ $OSTYPE == "cygwin" ]]
then
    PATHSEP=";"
fi

JAVA_FILES="jsonexample/*/*.java"
MAIN_CLASS="jsonexample.main.CreateSchedule"
ORG_JSON="org.json.jar"
SQLITE_JAR="sqlite-jdbc.jar"
CP=".${PATHSEP}${ORG_JSON}${PATHSEP}"
javac -cp "${CP}" ${JAVA_FILES} && java -cp "${CP}" ${MAIN_CLASS}
