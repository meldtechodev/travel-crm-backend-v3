FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:19.0.1-jdk-slim
COPY --from=build /target/TravelCRMV3-0.0.1-SNAPSHOT.jar TravelCRMV3.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar","TravelCRMV3.jar"]