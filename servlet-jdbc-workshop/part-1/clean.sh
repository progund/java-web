#!/bin/bash

find . -name '*~' -or -name '*.class' | xargs rm -f
