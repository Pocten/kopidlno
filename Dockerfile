# Using the official OpenJDK image as a base
FROM openjdk:17-jdk-slim

# Set the directory for our application
WORKDIR /app

# Copy the assembly file (JAR) into the container
COPY target/kopidlno-0.0.1-SNAPSHOT.jar /app/kopidlno.jar

# Command to run Spring Boot application
ENTRYPOINT ["java", "-jar", "/app/kopidlno.jar"]
