FROM openjdk:21

# Set working directory to /app inside the container
WORKDIR /app

# Copy the JAR file from the host's target directory to /app in the container
COPY target/scm2.0-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8080 to the host
EXPOSE 8080

# Entry point to run the JAR file
ENTRYPOINT ["java", "-jar", "/app/app.jar"]