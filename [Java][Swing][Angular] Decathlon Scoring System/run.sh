#!/bin/bash
rm -f ./data.txt
mvn clean
mvn compile
mvn test
mvn exec:java -Dexec.mainClass="generator.FakeAthleteGenerator" -Dexec.args="20" 
mvn exec:java -Dexec.mainClass="calculator.gui.DecathlonFrame" &
mvn exec:java -Dexec.mainClass="main.Application"
sensible-browser ./final_ranking.htm &> /dev/null
