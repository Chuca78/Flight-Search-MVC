package com.example.flightsearchmvc.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration-style test for FlightApiController's /api/search endpoint.
 *
 * <p>Uses MockMvc to simulate HTTP requests and verify correct JSON responses.
 * Assumes Spring Boot context is correctly wired with FlightSearchService.</p>
 */
@SpringBootTest
@AutoConfigureMockMvc
class FlightControllerTest {

    @Autowired
    private MockMvc mockMvc;

    /**
     * Verifies that the /api/search endpoint returns 200 OK for valid JSON input.
     * Also checks that the response is in JSON format.
     */
    @Test
    void searchFlights_returnsOk() throws Exception {
        String json = "{ \"origin\": \"New York\", \"destination\": \"London\", \"date\": \"2025-05-01\", \"passengers\": 1 }";

        mockMvc.perform(post("/api/search")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
}
