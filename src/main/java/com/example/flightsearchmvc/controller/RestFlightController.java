package com.example.flightsearchmvc.controller;

import com.example.flightsearchmvc.model.FlightResult;
import com.example.flightsearchmvc.model.FlightSearchRequest;
import com.example.flightsearchmvc.service.FlightSearchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller that exposes a flight search API for programmatic access.
 * This controller handles JSON-based requests and returns flight results in JSON format.
 */
@RestController
@RequestMapping("/api")
public class RestFlightController {

    private final FlightSearchService flightSearchService;

    /**
     * Constructor-based injection of the flight search service.
     *
     * @param flightSearchService Service for handling flight data retrieval
     */
    public RestFlightController(FlightSearchService flightSearchService) {
        this.flightSearchService = flightSearchService;
    }

    /**
     * POST endpoint that accepts a flight search request and returns matching results.
     * The search source can be set to "amadeus" or fallback to local data.
     *
     * @param request The search criteria submitted by the user
     * @param source  Optional source flag ("amadeus" or "local")
     * @return JSON response with list of matching flights or error message
     */
    @PostMapping("/search")
    public ResponseEntity<?> searchFlights(
            @ModelAttribute FlightSearchRequest request,
            @RequestParam(value = "source", defaultValue = "local") String source) {
        try {
            List<FlightResult> results = "amadeus".equalsIgnoreCase(source)
                    ? flightSearchService.searchWithAmadeus(request)
                    : flightSearchService.searchFlights(request);

            return ResponseEntity.ok(results);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
}
