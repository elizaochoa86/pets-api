FROM openjdk:8-jdk-alpine
ARG JAR_FILE=pets-api-1.0-SNAPSHOT.jar
ADD target/${JAR_FILE} /app.jar
ENV DATABASE_HOST localhost
ENTRYPOINT ["java", "-jar", "/app.jar"]