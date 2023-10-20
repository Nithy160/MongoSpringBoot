FROM openjdk:21-jdk-slim
ADD target/springboot-mongo-docker.jar springdocker.jar
#EXPOSE 8091
ENTRYPOINT ["java","-jar","springdocker.jar"]