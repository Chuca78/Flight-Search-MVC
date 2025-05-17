package com.example.flightsearchmvc.model;

import jakarta.validation.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link FlightSearchRequest}.
 * Verifies that input constraints (e.g., @NotNull, @Min) are enforced.
 */
class FlightSearchRequestTest {

    private static Validator validator;

    /**
     * Initializes a shared Validator instance used for validating test inputs.
     */
    @BeforeAll
    static void setupValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    /**
     * Ensures a fully populated and valid FlightSearchRequest passes validation.
     */
    @Test
    void validRequest_shouldPassValidation() {
        FlightSearchRequest request = new FlightSearchRequest();
        request.setOrigin("New York");
        request.setDestination("London");
        request.setDate(LocalDate.now());
        request.setPassengers(1);

        Set<ConstraintViolation<FlightSearchRequest>> violations = validator.validate(request);
        assertTrue(violations.isEmpty(), "Valid request should not trigger validation errors");
    }

    /**
     * Ensures that a request missing required fields fails validation.
     */
    @Test
    void missingRequiredFields_shouldFailValidation() {
        FlightSearchRequest request = new FlightSearchRequest(); // No fields populated

        Set<ConstraintViolation<FlightSearchRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty(), "Missing fields should result in validation errors");
    }

    /**
     * Ensures that setting passengers to a negative value fails validation.
     */
    @Test
    void negativePassengers_shouldFailValidation() {
        FlightSearchRequest request = new FlightSearchRequest();
        request.setOrigin("New York");
        request.setDestination("London");
        request.setDate(LocalDate.now());
        request.setPassengers(-1); // Invalid input

        Set<ConstraintViolation<FlightSearchRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty(), "Negative passengers should fail validation");
    }
}
