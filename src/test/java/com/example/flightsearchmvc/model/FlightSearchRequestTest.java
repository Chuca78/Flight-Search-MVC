package com.example.flightsearchmvc.model;

import jakarta.validation.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link FlightSearchRequest}.
 * Validates form constraints such as @NotNull and @Min for user-submitted search data.
 */
class FlightSearchRequestTest {

    private static Validator validator;

    /**
     * Initializes the Validator instance once for all test methods.
     */
    @BeforeAll
    static void setupValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    /**
     * Tests that a properly filled form request passes all validation constraints.
     */
    @Test
    void validRequest_shouldPassValidation() {
        FlightSearchRequest request = new FlightSearchRequest();
        request.setOrigin("JFK");
        request.setDestination("LAX");
        request.setDate(LocalDate.now());
        request.setPassengers(1);

        Set<ConstraintViolation<FlightSearchRequest>> violations = validator.validate(request);
        assertTrue(violations.isEmpty(), "Valid request should not produce validation errors");
    }

    /**
     * Tests that a request missing all required fields fails validation.
     */
    @Test
    void missingRequiredFields_shouldFailValidation() {
        FlightSearchRequest request = new FlightSearchRequest(); // Empty object

        Set<ConstraintViolation<FlightSearchRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty(), "Missing required fields should trigger validation errors");
    }

    /**
     * Tests that a negative number of passengers fails the @Min constraint.
     */
    @Test
    void negativePassengers_shouldFailValidation() {
        FlightSearchRequest request = new FlightSearchRequest();
        request.setOrigin("SEA");
        request.setDestination("ORD");
        request.setDate(LocalDate.now());
        request.setPassengers(-1); // Invalid passengers

        Set<ConstraintViolation<FlightSearchRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty(), "Negative passengers should not pass validation");
    }
}
