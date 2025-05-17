package com.example.flightsearchmvc.controller;

import com.example.flightsearchmvc.model.FlightSearchRequest;
import com.example.flightsearchmvc.service.FlightSearchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Unit tests for {@link FlightController}, which handles the
 * user-facing form-based flight search functionality.
 */
@WebMvcTest(FlightController.class)
class FlightControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @MockitoBean
    private FlightSearchService flightSearchService;

    /**
     * Initializes MockMvc using the application context before each test.
     */
    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    /**
     * Verifies that a valid form submission:
     * - Returns HTTP 200
     * - Renders the "search" view
     * - Populates the model with flight results
     */
    @Test
    void processSearch_validInput_returnsSearchViewWithResults() throws Exception {
        Mockito.when(flightSearchService.searchWithAmadeus(any(FlightSearchRequest.class)))
                .thenReturn(List.of()); // Simulate an empty result list

        mockMvc.perform(post("/search")
                        .param("origin", "CID")
                        .param("destination", "CLT")
                        .param("date", "2025-05-05")
                        .param("passengers", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("search"))
                .andExpect(model().attributeExists("flightResults"));
    }

    /**
     * Verifies that invalid input (missing required fields):
     * - Returns HTTP 200
     * - Renders the "search" view
     * - Provides a model error attribute
     */
    @Test
    void processSearch_invalidInput_returnsSearchViewWithError() throws Exception {
        mockMvc.perform(post("/search")
                        .param("origin", "")          // Required field missing
                        .param("destination", "CLT")
                        .param("date", "")             // Required field missing
                        .param("passengers", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("search"))
                .andExpect(model().attributeExists("error"));
    }
}
