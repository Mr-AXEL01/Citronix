FROM maven:3.9.2-eclipse-temurin-17-alpine AS maven
WORKDIR /app
COPY ./pom.xml ./pom.xml

COPY ./src .

RUN mvn dependency:go-offline -B

RUN mvn clean package -X

# Use an official OpenJDK runtime as the base image
FROM openjdk:21-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the application JAR file into the container
COPY target/CITRONIX-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your app runs on (e.g., 8080)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
