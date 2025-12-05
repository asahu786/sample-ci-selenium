# Use an official OpenJDK runtime as a base image
FROM eclipse-temurin:17-jdk-alpine


# Set working directory inside the container
WORKDIR /app

## Copy the built JAR from the app module
COPY app/target/spring-boot-app-3.3.2.jar app.jar


# Expose the port your Spring Boot app runs on ok fine
EXPOSE 8080

# Run the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
