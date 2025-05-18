package com.example.flightsearchmvc.controller;

import com.example.flightsearchmvc.model.FlightResult;
import com.example.flightsearchmvc.model.FlightSearchRequest;
import com.example.flightsearchmvc.service.FlightSearchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller that exposes a flight search API for programmatic access.
 * Accepts HTTP POST requests with flight search criteria and returns results in JSON format.
 * This endpoint is useful for integrating with external systems or clients.
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
     * Handles POST requests to search for flights based on user input.
     * Accepts either Amadeus API or local dummy data as a source.
     *
     * @param request Flight search criteria including origin, destination, date, and passengers
     * @param source  Optional source flag: "amadeus" for live data or "local" for fallback
     * @return 200 OK with flight results, or 500 Internal Server Error with an error message
     */
    @PostMapping("/search")
    public ResponseEntity<?> searchFlights(
            @ModelAttribute FlightSearchRequest request,
            @RequestParam(value = "source", defaultValue = "local") String source) {
        try {
            // Determine the source of flight data (Amadeus API or local fallback)
            List<FlightResult> results = "amadeus".equalsIgnoreCase(source)
                    ? flightSearchService.searchWithAmadeus(request)
                    : flightSearchService.searchFlights(request);

            // Return successful JSON response
            return ResponseEntity.ok(results);

        } catch (Exception e) {
            // Return generic error with status 500 and exception message
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
}
