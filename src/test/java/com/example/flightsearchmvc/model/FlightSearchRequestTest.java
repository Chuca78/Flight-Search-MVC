package com.example.flightsearchmvc.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import jakarta.validation.*;
import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for validating constraints on the FlightSearchRequest model.
 *
 * <p>Tests ensure that Bean Validation annotations on the request object
 * enforce proper input requirements for the flight search form/API.</p>
 */
class FlightSearchRequestTest {

    private static Validator validator;

    /**
     * Initializes the validator factory for use in all tests.
     */
    @BeforeAll
    static void setupValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    /**
     * Verifies that a well-formed FlightSearchRequest passes validation.
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
     * Verifies that leaving all fields unset triggers validation failures.
     */
    @Test
    void missingRequiredFields_shouldFailValidation() {
        FlightSearchRequest request = new FlightSearchRequest();

        Set<ConstraintViolation<FlightSearchRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty(), "Missing fields should cause violations");
    }

    /**
     * Verifies that setting a negative passenger count fails validation.
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
