# Use Maven with Java 21 to build the project
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

# Copy everything from the current directory (should include pom.xml and src/)
COPY . .

# Build the app, skipping tests (optional)
RUN mvn clean package

# Use lightweight Java 21 runtime to run the built jar
FROM eclipse-temurin:21-jdk-slim

WORKDIR /app

# Copy the built JAR file from previous stage
COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar bank_system.jar

# Expose port 8080 (for Spring Boot)
EXPOSE 8080

# Run the app
ENTRYPOINT ["java", "-jar", "bank_system.jar"]
