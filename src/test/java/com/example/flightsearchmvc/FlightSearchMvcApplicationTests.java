package com.example.flightsearchmvc;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Basic smoke test to verify that the Spring application context loads successfully.
 *
 * <p>This test ensures that all beans and configurations are valid and the application
 * can start without runtime exceptions. Required by Spring Boot test conventions.</p>
 */
@SpringBootTest
class FlightSearchMvcApplicationTests {

    /**
     * Loads the application context.
     *
     * <p>Will fail if any Spring beans are misconfigured or missing.</p>
     */
    @Test
    void contextLoads() {
        // Context loading is automatically validated by the test runner
    }
}
