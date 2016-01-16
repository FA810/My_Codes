#!/bin/bash
# Maven repository must be in $HOME/.m2/repository/

java -Dfile.encoding=UTF-8 -classpath ./target/test-classes:./target/classes:$HOME/.m2/repository/junit/junit/3.8.1/junit-3.8.1.jar:$HOME/.m2/repository/org/hsqldb/hsqldb/2.3.3/hsqldb-2.3.3.jar com.fabio.connectors.SocketClient
