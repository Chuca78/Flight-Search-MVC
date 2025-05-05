package com.example.flightsearchmvc;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Basic integration test to verify that the Spring Boot application context loads successfully.
 * This ensures all configuration and bean initialization is valid.
 */
@SpringBootTest
class FlightSearchMvcApplicationTests {

    /**
     * Verifies that the Spring application context can load without errors.
     * This test is useful as a baseline health check of the overall configuration.
     */
    @Test
    void contextLoads() {
        System.out.println("contextLoads test executed successfully.");
        assert true;  // Simple assertion to confirm test ran
    }
}
