package com.example.flightsearchmvc.controller;

import com.example.flightsearchmvc.model.FlightResult;
import com.example.flightsearchmvc.model.FlightSearchRequest;
import com.example.flightsearchmvc.service.FlightSearchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller that exposes a flight search endpoint for external clients.
 * Accepts POST requests and returns flight results in JSON format.
 * Supports real-time (Amadeus) and local fallback data sources.
 */
@RestController
@RequestMapping("/api")
public class RestFlightController {

    private final FlightSearchService flightSearchService;

    /**
     * Constructor for injecting the flight search service.
     *
     * @param flightSearchService Service responsible for retrieving flight results
     */
    public RestFlightController(FlightSearchService flightSearchService) {
        this.flightSearchService = flightSearchService;
    }

    /**
     * Handles flight search requests via POST. Accepts flight parameters and a source flag,
     * then returns flight results in JSON format.
     *
     * @param request User-submitted flight search form data
     * @param source  Optional: "amadeus" for real data, "local" for fallback
     * @return HTTP 200 with JSON list of results, or HTTP 500 with error message
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
