# Use slim OpenJDK base
FROM openjdk:17-jdk-slim

# Create app working directory
WORKDIR /app

# Copy fat JAR and logging config
COPY target/scala-2.13/*.jar app.jar
COPY config/logback.xml ./logback.xml

# Set env vars (can be overridden at runtime)
ENV GITHUB_TOKEN=unset
ENV LOG_LEVEL=INFO
ENV JAVA_OPTS="-Dlogback.configurationFile=logback.xml"

# Launch the app
CMD ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
