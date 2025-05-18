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
- [x] Perform full form validation using Jakarta Validation API
- [x] Add additional unit tests to cover added controllers
- [x] Give user meaningful errors (eg. when searching past travel dates)
- [x] Handle invalid inputs with UI error messaging
- [x] Structure exception handling and error messages in REST and MVC controllers
- [x] Verify that the app runs cleanly with no runtime or test errors
- [x] Final polish for UI clarity and responsiveness (if needed)

---

## USER TESTING FEEDBACK (Consider implementing fixes):
- [ ] Double check pricing (seems too cheap, could be an API translation problem)
- [ ] Ensure the ticket price multiplies by the number of passengers
- [ ] Update error messages to be more meaningful (Unable to fetch flight data. Please check your input or try again later.)
        This message is present when user inputs city name instead of airport code, user doesn't know why there is an error
- [ ] Consider changing location of calendar and passenger counter (One user didn't see the controls)
- [ ] Fix date/tine output - it comes directly from API in a format that is hard to read
- [ ] Fix airline output for search results (output as airline code which is not meaningful to users)
- [ ] Consider making the whole result card clickable instead of just the book now button
- [ ] Consider card spacing (Too much white space?)
- [ ] Consider updating logic to include search by city as well as airport code
- [ ] If not implementing city name support, clarify placeholder/help text (e.g., "Enter 3-letter airport code")
- [ ] Allow user to print or download booking information
- [ ] Review logic for results (why five? what is the order? is there a better way to output?)

## Final Submission: Clean-Up and Packaging
TODO (Mandatory):
- [ ] Clean up project files, remove unused assets
- [ ] Review and polish all UI and templates for visual consistency and usability
- [ ] Add optional JavaScript validation for:
  - [ ] Disallowing past travel dates on the client side
  - [ ] Preventing empty form submissions (redundant with backend, but improves UX)
- [ ] Perform a full test run:
  - [ ] Web form (`/`)
  - [ ] REST API (`/api/search`)
  - [ ] Auth API (`/api/auth/login`, `/api/auth/register`)
- [ ] Update `README.md` with any last changes or improvements

---