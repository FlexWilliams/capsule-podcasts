FROM openjdk:15-jdk-alpine

RUN addgroup -S spring && adduser -S spring -G spring

USER spring:spring

ARG WAR_FILE=build/libs/*.war

COPY ${WAR_FILE} app.war

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.war"]