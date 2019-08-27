#!/bin/bash

cd ${API_DIR}

mvn dependency:resolve
mvn verify

cd src
mvn package

cd ..
/usr/lib/jvm/java-8-openjdk-amd64/bin/java -jar target/api-jar-with-dependencies.jar
