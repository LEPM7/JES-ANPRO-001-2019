FROM openjdk:8 

# Install maven
RUN apt-get update
RUN apt-get install -y maven