# mrivals-builder-back

This is the backend for the Marvel Rivals Builder project, built with Java and Spring Boot.

## Prerequisites

- **Java Development Kit (JDK)**: Version 17 or higher.
- **Maven**: (Optional if using the provided `mvnw` wrapper).

## Configuration

Ensure you have configured your database connection in `src/main/resources/application.properties` (or `application.yml`).

## Running the Application

You can run the application using the Maven wrapper included in the project:

### Windows
```powershell
.\mvnw spring-boot:run
```

### Linux / macOS
```bash
./mvnw spring-boot:run
```

The application will typically start on port `8080`.

## Building the JAR

To build the executable JAR file:

### Windows
```powershell
.\mvnw clean install
```

### Linux / macOS
```bash
./mvnw clean install
```

The compiled JAR file will be located in the `target/` directory.

## Docker

To build the Docker image (if a `Dockerfile` is present):

```bash
docker build -t mrivals-builder-back .
```
