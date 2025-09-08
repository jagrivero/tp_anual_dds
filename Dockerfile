FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src src
RUN mvn clean package -DskipTests
FROM openjdk:17-jdk
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
ENV URL_PROCESADOR=https://tp-dds-2025-procesadorpdi.onrender.com
ENV DD_AGENT_MAJOR_VERSION=7
ENV DD_API_KEY=${DD_API_KEY}
ENV DD_SITE=${DD_SITE}
RUN bash -c "$(curl -L https://s3.amazonaws.com/dd-agent/scripts/install_script.sh)"
EXPOSE 8080
ENTRYPOINT bash -c "/etc/init.d/datadog-agent start && java -jar /app/app.jar"
