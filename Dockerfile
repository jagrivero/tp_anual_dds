# Stage 1: Build con Maven
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copiamos solo pom.xml para cache de dependencias
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiamos el código y construimos el jar
COPY src src
RUN mvn clean package -DskipTests

# Copiamos el Java Agent
COPY datadog/dd-java-agent.jar /datadog/dd-java-agent.jar

# Stage 2: Runtime
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copiamos el jar compilado y el Java Agent
COPY --from=build /app/target/*.jar app.jar
COPY --from=build /datadog/dd-java-agent.jar /datadog/dd-java-agent.jar

ENV URL_PROCESADOR=https://tp-dds-2025-procesadorpdi.onrender.com

EXPOSE 8080
#sw
# ENTRYPOINT usando Java Agent, todo en una línea para que no haya parse errors
ENTRYPOINT ["java","-javaagent:/datadog/dd-java-agent.jar","-Ddd.service=mi-servicio","-Ddd.env=production","-Ddd.version=1.0.0","-jar","/app/app.jar"]
