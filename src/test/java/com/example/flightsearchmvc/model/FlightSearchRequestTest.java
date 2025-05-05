package com.example.flightsearchmvc.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import jakarta.validation.*;
import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for validating the FlightSearchRequest model.
 * These tests ensure that the input constraints defined via annotations
 * are properly enforced.
 */
class FlightSearchRequestTest {

    // Shared validator instance used for all tests
    private static Validator validator;

    /**
     * Initializes the validator factory before all tests.
     * This setup runs only once.
     */
    @BeforeAll
    static void setupValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    /**
     * Test that a properly populated FlightSearchRequest passes validation.
     */
    @Test
    void validRequest_shouldPassValidation() {
        FlightSearchRequest request = new FlightSearchRequest();
        request.setOrigin("New York");
        request.setDestination("London");
        request.setDate(LocalDate.now());
        request.setPassengers(1);

        Set<ConstraintViolation<FlightSearchRequest>> violations = validator.validate(request);
        assertTrue(violations.isEmpty(), "Valid request should have no violations");
    }

    /**
     * Test that leaving all fields empty causes validation to fail.
     */
    @Test
    void missingRequiredFields_shouldFailValidation() {
        FlightSearchRequest request = new FlightSearchRequest();
        // No fields set

        Set<ConstraintViolation<FlightSearchRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty(), "Missing fields should cause violations");
    }

    /**
     * Test that setting passengers to a negative number triggers a validation error.
     */
    @Test
    void negativePassengers_shouldFailValidation() {
        FlightSearchRequest request = new FlightSearchRequest();
        request.setOrigin("New York");
        request.setDestination("London");
        request.setDate(LocalDate.now());
        request.setPassengers(-1);

        Set<ConstraintViolation<FlightSearchRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty(), "Negative passengers should cause violations");
    }
}
