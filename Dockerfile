FROM openjdk:17-jdk
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

ENV URL_PROCESADOR=https://tp-dds-2025-procesadorpdi.onrender.com
ENV DD_AGENT_MAJOR_VERSION=7
# La DD_API_KEY y DD_SITE se pasan desde Render o docker run
# ENV DD_API_KEY=tu_api_key
# ENV DD_SITE=us3.datadoghq.com

EXPOSE 8080
ENTRYPOINT bash -c "\
  curl -L https://s3.amazonaws.com/dd-agent/scripts/install_script_agent7.sh | bash && \
  /etc/init.d/datadog-agent start && \
  java -jar /app/app.jar"
