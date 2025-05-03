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
- [x] Implemented REST API at `/api/search`
- [x] Integrated H2 database with `FlightRepository`
- [x] Updated `FlightSearchService` to query database
- [x] Seeded database with `data.sql`
- [x] Connected backend to UI via `RestTemplate`
- [x] Rendered flight results dynamically in Thymeleaf
- [x] Added validation to `FlightSearchRequest`
- [x] Added unit and integration tests:
  - FlightSearchServiceTest
  - FlightSearchServiceIntegrationTest
  - FlightControllerTest
  - FlightSearchRequestTest
  - FlightRepositoryTest
- [x] Added aria-labels for accessibility
- [x] Enhanced Javadoc and inline comments across backend

## Milestone Three: Security, Validation, and Final Enhancements
- [ ] Add user login and authentication (Spring Security)
- [ ] Create user registration flow (HTML + controller)
- [ ] Associate flight booking with authenticated user
- [ ] Implement booking confirmation view and backend logic
- [ ] Create `Booking` model, repository, and service
- [ ] Add navbar for login/logout, user dashboard
- [ ] Add client-side validation (JavaScript or HTML5)
- [ ] Refactor service logic for modularity if needed
- [ ] Expand integration tests for booking flow

---

## Final Submission: Clean-Up and Packaging
To Do:
- [ ] Add project-level `TODO.md` and milestone checklist (in work)
- [ ] Update `README.md` with final deployment steps
- [ ] Include accessibility audit summary (Lighthouse or manual)
- [ ] Create final ZIP containing all source files and documentation
- [ ] Perform final run-through with working search, dummy results, and clean UI

---
