package com.example.flightsearchmvc.controller;

import com.example.flightsearchmvc.model.FlightSearchRequest;
import com.example.flightsearchmvc.service.FlightSearchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration test for {@link FlightController}, which handles
 * user-facing form-based flight search via Thymeleaf.
 */
@SpringBootTest
@AutoConfigureMockMvc
class FlightControllerTest {

    private final WebApplicationContext context;
    private MockMvc mockMvc;

    @MockitoBean
    private FlightSearchService flightSearchService;

    public FlightControllerTest(WebApplicationContext context) {
        this.context = context;
    }

    /**
     * Initializes MockMvc before each test using the full application context.
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
                .thenReturn(List.of()); // Simulate empty results for valid search

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
     * Verifies that missing required form inputs:
     * - Return HTTP 200
     * - Redisplay the "search" view
     * - Populate the model with an error message
     */
    @Test
    void processSearch_invalidInput_returnsSearchViewWithError() throws Exception {
        mockMvc.perform(post("/search")
                        .param("origin", "")           // Missing origin
                        .param("destination", "CLT")
                        .param("date", "")             // Missing date
                        .param("passengers", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("search"))
                .andExpect(model().attributeExists("error"));
    }
}
