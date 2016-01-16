#!/bin/bash
# /usr/lib/jvm/java-7-openjdk-i386/bin/java -Dfile.encoding=UTF-8 -classpath /home/fabio/workspace/clientServer/bin connectors.SocketClient
#java -Dfile.encoding=UTF-8 -classpath /home/fabio/workspace/clientServer/bin connectors.SocketClient

 /usr/lib/jvm/java-7-oracle/bin/java -Dfile.encoding=UTF-8 -classpath /home/fabio/workspace/clientServer/target/test-classes:/home/fabio/workspace/clientServer/target/classes:/home/fabio/.m2/repository/junit/junit/3.8.1/junit-3.8.1.jar:/home/fabio/.m2/repository/org/hsqldb/hsqldb/2.3.3/hsqldb-2.3.3.jar com.fabio.connectors.SocketClient
