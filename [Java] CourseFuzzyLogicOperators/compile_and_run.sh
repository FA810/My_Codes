#!/bin/bash

mkdir bin 2> /dev/null
javac -d ./bin $(find . -name '*.java') 
java -Dfile.encoding=UTF-8 -classpath ./bin courseexamples.fuzzyOperator
