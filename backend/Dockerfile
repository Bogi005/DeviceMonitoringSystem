# BUILD THE BACKEND APP
FROM maven:3.9-eclipse-temurin-21-alpine AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn package -DskipTests

# PACKAGE IT AS A DOCKER IMAGE
# Base image
FROM eclipse-temurin:21-jdk-alpine
# Working directory inside the container
WORKDIR /app
# Copy the app in the container
COPY --from=build /app/target/*.jar app.jar
# Open port 8080
EXPOSE 8080
# Start the app
ENTRYPOINT ["java", "-jar", "app.jar"]