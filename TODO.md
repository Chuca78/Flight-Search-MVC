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
- [x] Handle invalid inputs with UI error messaging
- [x] Structure exception handling and error messages in REST and MVC controllers
- [x] Verify that the app runs cleanly with no runtime or test errors
- [x] Final polish for UI clarity and responsiveness (if needed)
- [x] Add additional unit tests to  cover added controllers

---

## Final Submission: Clean-Up and Packaging
- [ ] Finalize `TODO.md` and ensure rubric alignment
- [ ] Update `README.md` with any last changes or improvements
- [ ] Consider updating logic to include search by city as well as airport code
- [ ] Add optional JavaScript validation for:
  - [ ] Disallowing past travel dates on the client side
  - [ ] Preventing empty form submissions (redundant with backend, but improves UX)
- [ ] Perform a full test run:
  - [ ] Web form (`/`)
  - [ ] REST API (`/api/search`)
  - [ ] Auth API (`/api/auth/login`, `/api/auth/register`)
- [ ] Clean up project files, remove unused assets
- [ ] Create `.zip` archive with:
  - Java source files
  - HTML/Thymeleaf templates
  - `application.properties`
  - `README.md` and `TODO.md`
  - Any test data or test files required for evaluation
- [ ] Review and polish all UI and templates for visual consistency and usability

---