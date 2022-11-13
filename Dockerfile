FROM openjdk:17-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
COPY /config/java.security /opt/openjdk-17/conf/security/java.security 
ENTRYPOINT ["java","-jar","/app.jar"]