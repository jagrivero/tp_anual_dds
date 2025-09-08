# Stage 1: Build con Maven
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src src
RUN mvn clean package -DskipTests

# Stage 2: Runtime
FROM openjdk:17-jdk-slim
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

ENV URL_PROCESADOR=https://tp-dds-2025-procesadorpdi.onrender.com

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]