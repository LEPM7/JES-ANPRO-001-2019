#!/bin/bash
# -- install dependences
cd ${API_DIR}

# Prepare by downloading dependencies
mvn dependency:resolve
mvn verify

# Adding source, compile and package into a fat jar
cd src
mvn package

cd ..
/usr/lib/jvm/java-8-openjdk-amd64/bin/java -jar target/api-jar-with-dependencies.jar
