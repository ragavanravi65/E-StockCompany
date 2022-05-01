FROM openjdk:8-jdk-alpine
LABEL maintainer="Ragavan"
EXPOSE 8080
ARG JAR_FILE=build/libs/*.jar
ADD ${JAR_FILE} company.jar
ENTRYPOINT ["java","-jar","/company.jar"]