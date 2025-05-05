package com.example.flightsearchmvc.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration-style test for the REST controller handling flight search requests.
 * This verifies the behavior of the /api/search endpoint.
 */
@SpringBootTest
@AutoConfigureMockMvc
class RestFlightControllerTest {

    private MockMvc mockMvc;

    // Automatically injected WebApplicationContext used to build MockMvc
    private final WebApplicationContext context;

    public RestFlightControllerTest(WebApplicationContext context) {
        this.context = context;
    }

    /**
     * Initializes the MockMvc object with the application context before each test.
     */
    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    /**
     * Verifies that a POST request with valid flight search parameters returns
     * an HTTP 200 response and a JSON response body.
     */
    @Test
    void searchFlights_returnsOk_withFormParams() throws Exception {
        mockMvc.perform(post("/api/search")
                        .param("origin", "CID")
                        .param("destination", "CLT")
                        .param("date", "2025-05-05")
                        .param("passengers", "1")
                        .param("source", "amadeus"))
                .andExpect(status().isOk())  // Ensures the request was successful
                .andExpect(content().contentTypeCompatibleWith("application/json")); // Ensures JSON response
    }
}
