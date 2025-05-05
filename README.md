# Flight Search MVC Application

This project is a Spring Boot web application that enables users to search for flights by entering origin, destination, date of travel, and number of passengers. The application integrates a RESTful backend and dynamic Thymeleaf-based frontend, representing a transition from a static prototype to a full-stack MVC architecture.  

## Features Implemented

- Spring Boot MVC architecture with Thymeleaf
- RESTful API endpoint at `/api/search`
- Amadeus API integration using `RestTemplate`
- Validated form input with `@NotNull` and `@Min` annotations
- Accessible HTML5 form with Bootstrap styling
- Clear error messages for invalid inputs
- Dynamic display of search results (airline, time, price)
- Server-side validation and error handling
- JUnit tests for controllers, services, and models
- All business logic is modularized and tested


## Technology Stack

- Java 21
- Spring Boot 3
- Maven
- Thymeleaf
- HTML5 / CSS3
- Bootstrap 5
- JUnit 5 + Mockito
- Manual test via Postman/curl
- Amadeus REST API (test endpoint)

## Project Structure

### Model
- `FlightSearchRequest.java` – Form input model with validation
- `FlightResult.java` – Structure for returned flight data

### View
- `index.html` – Main search page with results and error handling

### Controller
- `FlightController.java` – Handles form input and result display
- `RestFlightController.java` – Accepts `/api/search` POST and returns JSON

### Service
- `FlightSearchService.java` – Orchestrates search logic and handles Amadeus API
- `AmadeusFlightService.java` – Optional alternate service (archived)

### Config
- `AmadeusProperties.java` – Injects API keys and URL config from `application.properties`

### Static Resources
- `style.css` - Custom styles (dark slate, input backgrounds)  
- `app.js` (placeholder) -Reserved for future JS 

## Testing

The following test classes are included and pass:

- `FlightSearchServiceTest.java`
- `FlightSearchRequestTest.java`
- `FlightControllerTest.java`
- `RestFlightControllerTest.java`
- `FlightSearchMvcApplicationTests.java`

Run all tests:

```bash
./mvnw clean test
```

## How to Run

1. Clone the repository
2. Ensure you have Java 17+ and Maven installed
3. Build and run:

```bash
./mvnw clean install
./mvnw spring-boot:run
```

4. Visit:  
   [http://localhost:8080/](http://localhost:8080/)

## Using the API

- **Endpoint:** `POST /api/search`
- **Content-Type:** `application/x-www-form-urlencoded` (default from HTML form)
- **Example request using Postman or curl:**

```bash
curl -X POST http://localhost:8080/api/search \
     -d "origin=CID&destination=CLT&date=2025-05-05&passengers=1"
```

## Milestone Two Focus Summary

- Integrated real-time flight search with mock Amadeus API
- Implemented JSON response handling and parsing with Jackson
- Provided UI and REST endpoint with consistent behavior
- Ensured accessibility compliance and contrast visibility
- Included full unit test coverage with mock APIs
- Eliminated all code warnings and unused imports

## Known Limitations / Future Enhancements

- Carrier codes returned from Amadeus (e.g., “UA”) not yet mapped to full names
- No real authentication or login mechanism yet
- Booking system and persistence layer not implemented
- UI could benefit from additional feedback for slow API responses

---

## Contact

© 2025
Timothy Fleck  
[tfleck78@gmail.com](mailto:tfleck78@gmail.com)  
[https://github.com/Chuca78/Flight-Search-MVC](https://github.com/Chuca78/Flight-Search-MVC)

