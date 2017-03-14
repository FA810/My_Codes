#!/bin/bash

mkdir bin 2> /dev/null
javac -d ./bin $(find . -name '*.java')
cp background.jpg bin/com/various/javafx/background.jpg
cp Login.css bin/com/various/javafx/Login.css 
java -Dfile.encoding=UTF-8 -classpath ./bin com.various.javafx.JavaFxHelloWorld &
java -Dfile.encoding=UTF-8 -classpath ./bin com.various.javafx.JavaFxForm &
java -Dfile.encoding=UTF-8 -classpath ./bin com.various.javafx.JavaFxFormCSS &
