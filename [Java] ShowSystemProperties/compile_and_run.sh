#!/bin/bash
mkdir -p bin 2> /dev/null
javac -d bin -sourcepath src src/SysPropList.java
java -Dfile.encoding=UTF-8 -classpath bin SysPropList
