# Stage 1: Build con Maven
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copiamos solo pom.xml primero para cache de dependencias
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiamos el código y construimos el jar
COPY src src
RUN mvn clean package -DskipTests

# Stage 2: Runtime
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copiamos el jar compilado desde la etapa build
COPY --from=build /app/target/*.jar app.jar

# Variables de entorno de la app
ENV URL_PROCESADOR=https://tp-dds-2025-procesadorpdi.onrender.com
# Datadog variables se pasan desde Render en Environment Variables
# DD_API_KEY, DD_SITE, DD_AGENT_MAJOR_VERSION

EXPOSE 8080

# Arranca la app. Datadog Agent se levanta desde el host/Render
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
