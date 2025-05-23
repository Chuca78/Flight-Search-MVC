package com.example.flightsearchmvc;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Basic integration test to verify that the Spring Boot application context loads successfully.
 * This ensures all configuration and bean initialization is valid.
 */
@SpringBootTest
class FlightSearchMvcApplicationTests {

    /**
     * Verifies that the Spring application context can load without throwing exceptions.
     * Acts as a smoke test for application stability.
     */
    @Test
    void contextLoads() {
        assertTrue(true, "Spring context should load without errors");
    }
}
