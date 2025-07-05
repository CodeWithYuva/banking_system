# 1️⃣ Build stage using Maven with Java 21
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app
COPY . .

# Build the app without running tests
RUN mvn clean package -DskipTests

# 2️⃣ Runtime stage using Java 21
FROM eclipse-temurin:21-jdk

WORKDIR /app

# Copy the built JAR from the builder stage
COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar bank_system.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "bank_system.jar"]
