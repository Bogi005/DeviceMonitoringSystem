# Base image
FROM eclipse-temurin:21-jdk-alpine

# Working directory inside the container
WORKDIR /app

# Copy the app in the container
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# Open port 8080
EXPOSE 8080

# Start the app
ENTRYPOINT ["java", "-jar", "app.jar"]