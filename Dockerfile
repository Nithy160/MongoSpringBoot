FROM openjdk:21-jdk-slim
ADD target/springboot-mongo-docker.jar springdocker.jar
EXPOSE 5050
ENTRYPOINT ["java","-jar","springdocker.jar"]