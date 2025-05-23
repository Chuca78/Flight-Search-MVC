# Milestone-Based TODO List (Guided by Final Project Rubrics)

This file outlines TODO items for each milestone leading up to the final submission, aligned with the rubrics provided in the course. The file was created in the beginning of the project to organize and track application wide TODOs.

---

## Milestone One: Core Web Application and MVC Foundation

Completed:
- [x] Converted HTML/JS draft to Spring Boot MVC architecture
- [x] Created `FlightSearchRequest` and `FlightResult` models
- [x] Configured Thymeleaf form binding with `th:object`
- [x] Implemented dummy flight results through `FlightSearchService`
- [x] Styled flight results using Bootstrap card layout
- [x] Addressed contrast and accessibility issues in UI
- [x] Incorporated feedback from peers and instructor

---

## Milestone Two: RESTful Service and Backend Integration
Completed:
- [x] Created RESTful controller at `/api/search` returning JSON
- [x] Integrated Amadeus API with token-based authentication
- [x] Replaced dummy logic with live data from Amadeus for realistic output
- [x] Built `searchWithAmadeus()` and `getAccessToken()` methods in `FlightSearchService`
- [x] Supported fallback dummy results if needed via `searchFlights()`
- [x] Validated `FlightSearchRequest` using `@NotNull` and `@Min` annotations
- [x] Added unit tests:
  - `FlightSearchServiceTest`
  - `FlightSearchRequestTest`
  - `FlightControllerTest`
  - `RestFlightControllerTest`
  - `FlightSearchMvcApplicationTests` (context load test)
- [x] Commented all classes and methods for clarity and rubric compliance
- [x] Removed debug logging and cleaned up unused code
- [x] Added aria-labels for accessibility

---

## Milestone Three: Security, Validation, and Final Enhancements
- [x] Performed full form validation using Jakarta Validation API
- [x] Added additional unit tests to cover added controllers
- [x] Gave user meaningful errors (eg. when searching past travel dates)
- [x] Handled invalid inputs with UI error messaging
- [x] Structured exception handling and error messages in REST and MVC controllers
- [x] Verified that the app runs cleanly with no runtime or test errors
- [x] Final polish for UI clarity and responsiveness
- [x] Created my booking view so user can see what they have booked 

---

## Final Submission: Clean-Up and Packaging
- [x] Fixed date/time output - it comes directly from API in a format that is hard to read
- [x] Fixed airline output for search results (output as airline code which is not meaningful to users)
- [x] Added ticket price multiplier by the number of passengers
- [x] Cleaned up project files, remove unused assets
- [x] Reviewed and polish all UI and templates for visual consistency and usability
- [ ] Verify javadoc and inline commenting complete
- [x] Added JavaScript validation for:
  - [x] Disallowing past travel dates on the client side
  - [x] Preventing empty form submissions (redundant with backend, but improves UX)
- [x] Perform a full test run:
  - [x] Web form (`/`)
  - [x] REST API (`/api/search`)
  - [x] Auth API (`/api/auth/login`, `/api/auth/register`)
- [x] Updated `README.md` with any last changes or improvements

## USER TESTING FEEDBACK (Consider implementing fixes):
### Implemented
- [x] Update error messages to be more meaningful (Unable to fetch flight data. Please check your input or try again later.)
      - This message is present when user inputs city name instead of airport code, user doesn't know why there is an error
- [x] Allow user to print or download booking information
- [x] Give user a better loading indicator after search submitted
- [x] Review logic for results (why five? what is the order? is there a better way to output?)
      - Changed results to ten for variety and even results
      - ordered by cheapest first by default
      - considered implementing sorting options, but decided not to implement for this project

### Not Implemented
- [ ] Consider changing location of calendar and passenger counter (One user didn't see the controls)
- [ ] Consider making the whole result card clickable instead of just the book now button
- [ ] Consider card spacing (Too much white space?)
- [ ] Consider updating logic to include search by city as well as airport code
  - [ ] Did not implement city name support, but clarified placeholder/help text ("Enter a valid 3-letter airport code")

---