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
- [ ] Perform full form validation using Jakarta Validation API
- [ ] Handle invalid inputs with UI error messaging
- [ ] Structure exception handling and error messages in REST and MVC controllers
- [ ] Verify that the app runs cleanly with no runtime or test errors
- [ ] Add date validation rule (e.g., no past dates — optional)
- [ ] Consider front-end client validation for improved UX (HTML5 or JS)
- [ ] Final polish for UI clarity and responsiveness (if needed)

---

## Final Submission: Clean-Up and Packaging
To Do:
- [ ] Finalize this `TODO.md` and mark all rubric goals
- [ ] Update `README.md` with build/test/run instructions
- [ ] Create `.zip` archive with:
  - Java source files
  - HTML/Thymeleaf templates
  - `application.properties`
  - `README.md` and `TODO.md`
- [ ] Perform one last functional test for both `/` and `/api/search` with live and dummy modes
- [ ] Ensure clean and professional presentation for submission

---