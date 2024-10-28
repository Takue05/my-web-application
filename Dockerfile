# Build stage
FROM maven:3.6.3-openjdk-11 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package
EXPOSE 8080

# Production stage

FROM azul/zulu-openjdk:11.0.11

EXPOSE 8080

COPY ./target/web-app-docker.jar web-app-docker.jar

ENTRYPOINT ["java","-jar","web-app-docker.jar"]
