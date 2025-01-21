# Use Maven official image for building the application

FROM maven:3.9.4-eclipse-temurin-17 AS build
 
# Set the working directory in the build stage

WORKDIR /app
 
# Copy the Maven project files

COPY pom.xml .

COPY src ./src
 
# Build the application

RUN mvn clean package -DskipTests
 
# Use a smaller image for running the application

FROM openjdk:17-jdk-slim
 
# Set the working directory in the run stage

WORKDIR /app
 
# Copy the built JAR file from the build stage

COPY --from=build /app/target/*.jar app.jar
 
# Expose the application port

EXPOSE 8080
 
# Run the Spring Boot application

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
 
 