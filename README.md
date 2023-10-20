# MongoSpringBoot

Spring boot version used: 3.1.5
Java runtime: 21

This application uses a docker file to generate image springbootdocker. It connects to a mongodb running on a docker container.

Step 1: Run a docker container for mongodb with the below command.
docker run -d -p 27017:27017 --name mongodocker mongo:latest

Step 2: 
mvn clean install
docker run -d -p 8091:8091 --link mongodocker:mongo springbootdocker:1.0
