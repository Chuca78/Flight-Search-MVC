package com.example.flightsearchmvc.controller;

import com.example.flightsearchmvc.model.FlightResult;
import com.example.flightsearchmvc.service.FlightSearchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for {@link RestFlightController}.
 * These tests verify the behavior of the /api/search endpoint,
 * which provides JSON-formatted flight search results.
 */
@SpringBootTest
@AutoConfigureMockMvc
class RestFlightControllerTest {

    private MockMvc mockMvc;

    private final WebApplicationContext context;

    @MockitoBean
    private FlightSearchService flightSearchService;

    /**
     * Constructs the test with WebApplicationContext injected.
     *
     * @param context the Spring application context
     */
    public RestFlightControllerTest(WebApplicationContext context) {
        this.context = context;
    }

    /**
     * Initializes MockMvc and stubs the flight search service to return a mocked flight result.
     */
    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();

        // Return a dummy flight result when the Amadeus search is triggered
        when(flightSearchService.searchWithAmadeus(any())).thenReturn(
                List.of(new FlightResult("UA", "CID", "CLT", "08:00", "11:00", 350.00))
        );
    }

    /**
     * Simulates a POST request to /api/search using form parameters.
     * Asserts that the response is HTTP 200 OK and the content type is application/json.
     */
    @Test
    void searchFlights_returnsOk_withFormParams() throws Exception {
        mockMvc.perform(post("/api/search")
                        .param("origin", "CID")
                        .param("destination", "CLT")
                        .param("date", "2025-05-05")
                        .param("passengers", "1")
                        .param("source", "amadeus"))
                .andExpect(status().isOk()) // Expect 200 OK
                .andExpect(content().contentTypeCompatibleWith("application/json")); // Expect JSON content
    }
}
