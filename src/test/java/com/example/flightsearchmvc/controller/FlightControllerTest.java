package com.example.flightsearchmvc.controller;

import com.example.flightsearchmvc.model.FlightSearchRequest;
import com.example.flightsearchmvc.service.FlightSearchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Unit tests for the FlightController, which handles the HTML form-based flight search.
 * This test uses Spring's MockMvc to simulate HTTP requests and verify controller behavior.
 */
@WebMvcTest(FlightController.class)
class FlightControllerTest {

    private MockMvc mockMvc;

    // Mocks the FlightSearchService dependency inside FlightController
    @MockitoBean
    private FlightSearchService flightSearchService;

    /**
     * Initializes the MockMvc object using the web application context.
     */
    @BeforeEach
    void setUp(WebApplicationContext context) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    /**
     * Test that verifies valid search input returns the "index" view with flight results in the model.
     * The mocked service returns an empty result list.
     */
    @Test
    void processSearch_validInput_returnsIndexWithResults() throws Exception {
        Mockito.when(flightSearchService.searchWithAmadeus(any(FlightSearchRequest.class)))
                .thenReturn(List.of());

        mockMvc.perform(post("/search")
                        .param("origin", "CID")
                        .param("destination", "CLT")
                        .param("date", "2025-05-05")
                        .param("passengers", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("flightResults")); // Ensures results attribute is returned
    }

    /**
     * Test that verifies invalid input (missing required fields) triggers an error and returns to the form.
     */
    @Test
    void processSearch_invalidInput_returnsIndexWithError() throws Exception {
        mockMvc.perform(post("/search")
                        .param("origin", "")       // Origin is required
                        .param("destination", "CLT")
                        .param("date", "")          // Date is required
                        .param("passengers", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("error")); // Checks for presence of error message
    }
}
