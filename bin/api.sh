#!/bin/bash

cd ${API_DIR}

mvn install:install-file -Dfile=/opt/bin/sqljdbc4-2.0.jar -DgroupId=com.microsoft.sqlserver -DartifactId=sqljdbc4 -Dversion=4.0 -Dpackaging=jar

mvn dependency:resolve
mvn verify

cd src
mvn package

cd ..
/usr/lib/jvm/java-8-openjdk-amd64/bin/java -jar target/api-jar-with-dependencies.jar
