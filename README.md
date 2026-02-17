# Game Library HB - Handball League Management System

A Spring Boot application for managing a handball league, including teams, players, matches, and standings.

## Features

*   **Team Management**: Create, read, update, and delete handball teams.
*   **Player Management**: Manage players and assign them to teams.
*   **Match Recording**: Record match results and player statistics.
*   **Standings**: Automatically calculated league standings based on match results.
*   **REST API**: Full RESTful API for integration.
*   **Testing**: Comprehensive test suite including Unit Tests, Integration Tests (RestAssured), and BDD scenarios (Cucumber). (in progress)
*   **CICD with raporting(in progress)

## Tech Stack

*   **Java 21**
*   **Spring Boot 3.2.0** (Web, Data JPA, Security, Thymeleaf)
*   **Database**: MySQL (Production/Dev), H2 (Testing)
*   **Testing**: JUnit 5, RestAssured, Cucumber, Testcontainers (optional)
*   **Tools**: Maven, Docker, Lombok

## Getting Started

### Prerequisites

*   Java 21 SDK
*   Maven
*   Docker (for MySQL database)

### Running the Application

1.  **Start the Database**:
    Use Docker Compose to start the MySQL container.
    ```bash
    docker-compose up -d
    ```

2.  **Build the Project**:
    ```bash
    mvn clean install
    ```

3.  **Run the Application**:
    ```bash
    mvn spring-boot:run
    ```
    The application will be available at `http://localhost:8090`.

### API Documentation

Once the application is running, you can access the Swagger UI documentation at:
`http://localhost:8090/swagger-ui.html`

## Testing

The project includes a robust testing strategy.

### Running Unit & Integration Tests

To run standard JUnit and RestAssured tests:
```bash
mvn test
```

### Running Cucumber BDD Tests

To run only the Cucumber scenarios:
```bash
mvn test -Dtest=CucumberTestRunner
```

