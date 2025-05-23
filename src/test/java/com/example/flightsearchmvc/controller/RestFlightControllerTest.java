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
 * Verifies REST API behavior at /api/search endpoint using form parameters.
 */
@SpringBootTest
@AutoConfigureMockMvc
class RestFlightControllerTest {

    private MockMvc mockMvc;

    private final WebApplicationContext context;

    @MockitoBean
    private FlightSearchService flightSearchService;

    /**
     * Constructor injection of the application context.
     *
     * @param context Spring WebApplicationContext
     */
    public RestFlightControllerTest(WebApplicationContext context) {
        this.context = context;
    }

    /**
     * Sets up the test environment and mocks Amadeus API results.
     */
    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();

        // Return a dummy flight result for all Amadeus searches
        when(flightSearchService.searchWithAmadeus(any()))
                .thenReturn(List.of(new FlightResult("UA", "CID", "CLT", "08:00", "11:00", 350.00)));
    }

    /**
     * Validates that /api/search responds with 200 OK and returns JSON content
     * when valid parameters are posted.
     */
    @Test
    void searchFlights_returnsOk_withFormParams() throws Exception {
        mockMvc.perform(post("/api/search")
                        .param("origin", "CID")
                        .param("destination", "CLT")
                        .param("date", "2025-05-05")
                        .param("passengers", "1")
                        .param("source", "amadeus"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("application/json"));
    }
}
