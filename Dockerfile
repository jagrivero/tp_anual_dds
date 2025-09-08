# Stage 1: Build con Maven
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src src
RUN mvn clean package -DskipTests

# Stage 2: Runtime
FROM openjdk:17-jdk
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

ENV URL_PROCESADOR=https://tp-dds-2025-procesadorpdi.onrender.com
ENV DD_AGENT_MAJOR_VERSION=7
# DD_API_KEY y DD_SITE se deben pasar en runtime, no en build
EXPOSE 8080

ENTRYPOINT bash -c "\
  curl -L https://s3.amazonaws.com/dd-agent/scripts/install_script_agent7.sh | bash && \
  /etc/init.d/datadog-agent start && \
  java -jar /app/app.jar"
