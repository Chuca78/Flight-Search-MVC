# Flight Search MVC Application

This project is a Spring Boot web application that allows users to search for flights by entering origin, destination, date of travel, and number of passengers.  
**Milestone Two** demonstrates a fully functional REST API with Spring Data JPA and dynamic frontend rendering via Thymeleaf, progressing from a static UI prototype to a robust Java-based MVC architecture.

## Features Implemented

- Full-stack Java MVC using Spring Boot + Thymeleaf
- RESTful `/api/search` endpoint returns JSON flight results
- Flight search form with:
  - Origin and Destination fields
  - Date picker using HTML5 date input
  - Passenger count (1 - 10)
- Results dynamically retrieved via `RestTemplate` API call
- Display of multiple results with:
  - Airline name
  - Departure/arrival times
  - Price in USD
- Flight data persisted using Spring Data JPA with H2
- Accessible HTML with `aria-label` enhancements
- Responsive UI styled using Bootstrap 5

## Technology Stack

- Java 17
- Spring Boot
- Maven
- SpringMVC + Thymeleaf
- Spring Data JPA + H2 in-memory DB
- Bootstrap 5
- Maven
- JUnit 5 + Mockito
- Manual test via Postman/curl

## Project Structure

### Model
├── Flight.java                # Flight entity for database
├── FlightSearchRequest.java  # Form binding and input validation

### View
└── index.html                # Search form + dynamic results (Thymeleaf)

### Controller
├── FlightController.java     # Web UI controller (Thymeleaf)
├── FlightApiController.java  # REST API controller

### Service
└── FlightSearchService.java  # Search logic using FlightRepository

### Repository
└── FlightRepository.java     # JPA interface for flight queries

### Static Resources
├── application.properties    # H2 config and init behavior
├── data.sql                  # Seeded flight data
├── /static/css/style.css     # Custom UI styling

## Milestone Two Focus Areas

- Migrated from static HTML/CSS/JS to a full Java Spring Boot MVC architecture using Thymeleaf
- Implemented a **REST API endpoint** for searching flights (`/api/search`) using FlightApiController with request validation and JSON response support
- Integrated Spring Data JPA with an H2 in-memory database for flight persistence using Flight entity and FlightRepository
- Seeded the database with sample flight data via data.sql, including realistic times and prices
- Updated the FlightSearchService to query real database records instead of dummy data
- UI (FlightController) now uses RestTemplate to communicate with the API and dynamically render search results
- Enhanced accessibility with aria-label attributes and semantic HTML in index.html
- Developed a complete JUnit test suite, including:
  - Controller, service, repository, and model validation tests
  - Integration tests using H2 and Spring Boot context
- Refined Javadoc and inline comments across all classes for consistency and clarity
- Clearly separated concerns between model, view, controller, and service layers
- Documented all enhancements in TODO.md and maintained project alignment with milestone rubric
- Positioned for Milestone Three features: login system, booking flow, and advanced validations

## How to Run

1. Import project into your IDE
2. Ensure Java 17+ and Maven are installed
3. Delete /target (if present)
4. Build & run in terminal:
    ```bash
    ./mvnw clean install
    ./mvnw spring-boot:run
    ```
5. Open browser: [http://localhost:8080/](http://localhost:8080/)
      H2 console: `http://localhost:8080/h2-console`  
      (JDBC URL: `jdbc:h2:mem:flightsdb`)

## Using the REST API Directly

- **Endpoint:**  
  `POST http://localhost:8080/api/search`
- **Request Body (JSON):**
    ```json
    {
      "from": "JFK",
      "to": "LAX",
      "date": "2025-05-01",
      "passengers": 1
    }
    ```
- **Response Example:**
    ```json
    [
      {
        "airline": "British Airways",
      "origin": "New York",
      "destination": "London",
      "departureTime": "07:00",
      "arrivalTime": "19:30",
      "price": 500.0
      }
    ]
    ```
- **Test using Postman or curl:**
    ```bash
    curl  -X POST http://localhost:8080/api/search       
          -H "Content-Type: application/json"       
          -d '{"origin":"JFK","destination":"LAX","date":"2025-05-01","passengers":1}'
    ```
## Manual Testing Evidence

- All features work as described via browser (http://localhost:8080/) and direct API calls (Postman/curl)
- No critical errors in code or runtime

## Automated Testing

This project includes a comprehensive suite of JUnit-based tests to ensure correctness, reliability, and support for future development.

- **Controller tests:**  
  Confirm that /api/search returns the expected JSON output, handles validation errors properly, and integrates with the service layer without issues.

- **Service tests:**  
  Validate that FlightSearchService accurately processes user search requests, queries the repository correctly, and returns valid flight results (or empty lists for unmatched input).

- **Repository tests:**
  Verify that FlightRepository methods return correct matches based on origin, destination, and date using an in-memory H2 database.

- **Model validation tests:**  
  Ensure that constraints on FlightSearchRequest (e.g., required fields, positive passenger counts) are enforced and raise violations when missing or invalid.

**To run tests:**  
```bash
./mvnw clean test
```

## Known Limitations / TODOs

- No login/authentication yet
- Booking system is not implemented
- Dropdowns and client-side form validation not active
- Navbar and booking confirmation UI are placeholders
- Static airport options will be added in future version

---

## Contact

© 2025
Timothy Fleck  
[tfleck78@gmail.com](mailto:tfleck78@gmail.com)  
[https://github.com/Chuca78/Flight-Search-MVC](https://github.com/Chuca78/Flight-Search-MVC)
