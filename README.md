# Flight Search MVC Application

This Spring Boot web application allows users to search and book flights by entering origin, destination, travel date, and number of passengers. The app includes a secure registration and login system (using an XML file), real-time flight search via Amadeus API, and booking persistence to an H2 in-memory database. Built using MVC architecture with a responsive UI.

---

## Features Implemented

- **User Registration and Login**
  - XML-based persistence with `users.xml`
  - Auto-login after registration
  - Session tracking with login/logout behavior
- **Flight Search Functionality**
  - Validated search form for origin, destination, date, passengers
  - Real-time search with Amadeus REST API using `RestTemplate`
  - JSON parsing with Jackson and dynamic Thymeleaf display
- **Booking System**
  - "Book Flight" buttons available only to logged-in users
  - Booking confirmation view with flight details
  - Booking data saved in H2 database via Spring Data JPA
- **User Experience**
  - Session-aware navigation (welcome message, logout button)
  - Hero banner, contrast-friendly theme, Bootstrap 5 layout
  - Clear feedback for errors, success, and login state
- **Testing and Quality**
  - JUnit tests for service, model, controller layers
  - Clean, modular code with JavaDoc and inline comments
  - Accessible and responsive design

---

## Technology Stack

- Java 21
- Spring Boot 3.4
- Spring MVC + Thymeleaf
- Spring Data JPA + H2
- Maven
- Bootstrap 5
- Jackson, JAXB (XML)
- JUnit 5, Mockito

---

## Project Structure

### Model
- `FlightSearchRequest.java` – Form input model with validation
- `FlightResult.java` – Returned flight data structure
- `User.java` / `Users.java` – JAXB-bound models for login
- `Booking.java` – H2 entity for confirmed bookings

### Controller
- `FlightController.java` – Renders search form/results
- `LoginController.java` – Manages login, logout, registration
- `BookingController.java` – Handles post-booking and persistence
- `RestFlightController.java` – Exposes API endpoint at `/api/search`

### Service
- `FlightSearchService.java` – Handles flight search logic
- `AmadeusFlightService.java` – Token + HTTP integration with Amadeus
- `UserService.java` – Loads and saves user credentials to XML

### Repository
- `BookingRepository.java` – JPA interface for saving bookings

### Config
- `AmadeusProperties.java` – Injected config for API URLs & keys

---

 ## Testing

 The following test classes are included and passing:

 - `BookingControllerTest.java`
 - `UserServiceTest.java`
 - `FlightSearchServiceTest.java`
 - `FlightSearchRequestTest.java`
 - `FlightControllerTest.java`
 - `RestFlightControllerTest.java`
 - `FlightSearchMvcApplicationTests.java`

```bash
./mvnw clean test
```

---

##  How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/Chuca78/Flight-Search-MVC.git
   cd Flight-Search-MVC
   ```

2. Ensure Java 17+ and Maven are installed

3. Build and start the app:
   ```bash
   ./mvnw clean install
   ./mvnw spring-boot:run
   ```

4. Open in browser: [http://localhost:8080/](http://localhost:8080/)

5. Visit H2 console: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)  
   - JDBC URL: `jdbc:h2:mem:flightdb`  
   - Username: `sa`

---

## API Usage

- **POST** `/api/search`
- **Content-Type:** `application/x-www-form-urlencoded`
- **Example:**
   ```bash
   curl -X POST http://localhost:8080/api/search \
     -d "origin=CID&destination=CLT&date=2025-05-05&passengers=1"
   ```

---

## Milestone Three Focus Summary

- Added XML user registration and login system with session control
- Implemented "Book Flight" button for logged-in users only
+ Booking data saved to an in-memory H2 database via Spring Data JPA, visible through the `/h2-console`
- Displayed booking confirmation with details from session and POST
- Enabled and validated H2 console integration at `/h2-console`
- Implemented AuthRestController for RESTful login/register responses (no redirects)
- Added unit tests for authentication and booking logic, ensuring coverage of core logic
- Completed full Jakarta Validation of flight search form fields
- Reviewed and documented all major classes with JavaDoc and comments
- Cleaned up unused files and updated project structure

---

## Known Limitations / Future Enhancements

- **Carrier Code Mapping:** Airline codes returned from the Amadeus API (e.g., “UA”) are not mapped to full airline names like “United Airlines.” Adding a lookup or API integration could improve readability.
- **Lack of Visual Feedback:** The UI does not currently display a loading indicator or feedback during slow API responses, which may affect user experience.
+ **Session-Based Login Only:** The application uses basic session-based login validated against an XML file. It does not include Spring Security, token-based auth, or role-based access control.
- **No Booking History View:** Although flight bookings are saved to an H2 database, users currently have no way to view or manage their past bookings in the UI.
 - **No Front-End Date Validation:** The flight date input can be set to a past date. This is validated server-side but not blocked by the UI.

---

## Contact

© 2025  
Timothy Fleck  
[tfleck78@gmail.com](mailto:tfleck78@gmail.com)  
[https://github.com/Chuca78/Flight-Search-MVC](https://github.com/Chuca78/Flight-Search-MVC)
