# Kopidlno Project

This Spring Boot application processes and stores XML data into a PostgreSQL database. The project utilizes several modern technologies including Docker, Flyway, MapStruct, and is hosted on Render.com. This README provides a comprehensive guide on the setup, technologies used, and deployment process.

- **Live Demo:** [Swagger UI](https://kopidlno-app.onrender.com/swagger-ui/index.html)  
  **Note:** Please be patient, the page may take up to 50 seconds to load as the cloud service Render.com restarts the application upon receiving a request.

## Project Overview

- **Technologies Used:**
  - **Java 17:** The project is built using Java 17.
  - **Spring Boot:** A framework to build production-ready applications.
  - **Spring Data JPA:** For database interaction.
  - **Flyway:** For database migrations.
  - **MapStruct:** For object mapping.
  - **Docker:** To containerize the application.
  - **Render.com:** For hosting the application.
  - **PostgreSQL:** As the database.

 ## Project Setup

### Prerequisites

- Java 17
- Maven
- Docker
- Docker Compose

### Building the Project

1. **Clone the repository:**
   ```bash
   git clone https://github.com/Pocten/kopidlno.git
   cd kopidlno-project
   ```
   
2. **Build the project using Maven:**
   ```bash
   ./mvnw clean package
    ```
## Docker Setup

### Dockerfile
The Dockerfile is used to build the Docker image for the application.
```Dockerfile
# Using the official OpenJDK image as the base image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the build artifact into the Docker image
COPY target/kopidlno-0.0.1-SNAPSHOT.jar /app/kopidlno.jar

# Command to run the application
ENTRYPOINT ["java", "-jar", "/app/kopidlno.jar"]
```

### docker-compose.yml
The `docker-compose.yml` file is used to define and run multi-container Docker applications.
```yaml
version: '3.8'
services:
  app:
    build: .
    environment:
      - DATABASE_URL
      - DATABASE_USERNAME
      - DATABASE_PASSWORD
    ports:
      - "8080:8080"
```

## Running the Application with Docker
1. **Build the Docker image:**
   ```bash
   docker-compose build
    ```
2. **Start the application:**
   ```bash
   docker-compose up
    ```

## Endpoints
### Process Data Endpoint
- URL: `/api/v1/process-data`
- Method: GET
- Description: Processes the XML data and stores it in the database.

### Get All Districts
- URL: `/api/v1/districts`
- Method: GET
- Description: Retrieves all districts.

### Get All District Parts
- URL: `/api/v1/district-parts`
- Method: GET
- Description: Retrieves all district parts.

## Flyway Migration
The project uses Flyway for database migrations. Ensure that the migration files are located in `src/main/resources/db/migration`.
**Example Migration File: `V1__Create_tables.sql`**
```sql
CREATE TABLE district (
    code INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE district_part (
    code INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    district_code INT NOT NULL,
    FOREIGN KEY (district_code) REFERENCES district(code)
);
```
## Connecting to the Database
The application is configured to connect to a remote PostgreSQL database hosted on Aiven. Update the `application.properties` file with your database connection details if necessary.
```properties
spring.datasource.url=${DATABASE_URL}
spring.datasource.username=${DATABASE_USERNAME}
spring.datasource.password=${DATABASE_PASSWORD}

spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true
spring.jpa.open-in-view=false

# Flyway Configuration
spring.flyway.enabled=true
spring.flyway.locations=classpath:db/migration
spring.flyway.baseline-on-migrate=true
```
## License
This project was created by [Nikita Iastrebov](https://www.linkedin.com/in/nikita-iastrebov-0879001bb/) as a technical assignment for a job application at Trixi Software.
