#!/bin/bash

PATHSEP=":"
if [[ $OS == "Windows_NT" ]] || [[ $OSTYPE == "cygwin" ]]
then
    PATHSEP=";"
fi

javac -cp ".${PATHSEP}winstone.jar" webroot/WEB-INF/classes/ExampleServlet.java
