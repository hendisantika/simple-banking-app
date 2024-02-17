FROM maven:3.9.6-eclipse-temurin-21-alpine AS build

RUN mkdir /project

COPY . /project

# Passed from Github Actions
ARG GIT_VERSION_TAG=unspecified
ARG GIT_COMMIT_MESSAGE=unspecified
ARG GIT_VERSION_HASH=unspecified

WORKDIR /project

# You can read these files for the information in your application
RUN echo $GIT_VERSION_TAG > GIT_VERSION_TAG.txt
RUN echo $GIT_COMMIT_MESSAGE > GIT_COMMIT_MESSAGE.txt
RUN echo $GIT_VERSION_HASH > GIT_VERSION_HASH.txt

RUN mvn clean package

#FROM adoptopenjdk/openjdk21:eclipse-temurin-21-alpine
#FROM bellsoft/liberica-openjdk-debian:21
#FROM openjdk:21-slim
FROM amazoncorretto:21-alpine-jdk
LABEL maintainer="hendisantika@yahoo.co.id"

#RUN mkdir /app
#
#RUN addgroup -g 1001 -S hendigroup
#
#RUN adduser -S hendi -u 1001
#
#COPY --from=build /project/target/simple-banking-app-0.0.1.jar /app/simple-banking-app.jar
#
#WORKDIR /app
#
#RUN chown -R hendi:hendigroup /app

VOLUME /tmp
EXPOSE 8081
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} simple-banking-app.jar
ENTRYPOINT ["java","-jar","simple-banking-app.jar"]



CMD java $JAVA_OPTS -jar simple-banking-app.jar
