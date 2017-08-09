#!/bin/bash

echo "Simple example with users.xml"
javac -cp .:kxml2-2.3.0.jar XmlPullExample.java && java -cp .:kxml2-2.3.0.jar XmlPullExample
