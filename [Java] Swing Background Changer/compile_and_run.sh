#!/bin/bash
mkdir bin 2> /dev/null
javac -d ./bin ./src/BackgroundChangerFrame.java
java -Dfile.encoding=UTF-8 -classpath ./bin BackgroundChangerFrame
