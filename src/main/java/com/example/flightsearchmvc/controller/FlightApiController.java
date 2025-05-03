package com.example.flightsearchmvc.controller;

import com.example.flightsearchmvc.model.Flight;
import com.example.flightsearchmvc.model.FlightSearchRequest;
import com.example.flightsearchmvc.service.FlightSearchService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for handling flight search API requests.
 *
 * <p>Exposes a POST endpoint at <code>/api/search</code> to allow clients to submit
 * flight search parameters and receive a list of matching flight records in JSON format.</p>
 *
 * <p>Returns:
 * <ul>
 *   <li>HTTP 200 with a list of flights on success</li>
 *   <li>HTTP 400 with validation errors if the request is invalid</li>
 *   <li>HTTP 500 if an internal error occurs during processing</li>
 * </ul>
 * </p>
 */
@RestController
@RequestMapping("/api")
public class FlightApiController {

    @Autowired
    private FlightSearchService flightSearchService;

    /**
     * Accepts and validates flight search input, then returns matching flight results.
     *
     * @param request        the search criteria, including origin, destination, date, and passenger count
     * @param bindingResult  validation result for the request body
     * @return ResponseEntity containing:
     *         <ul>
     *             <li>200 OK with a list of flights</li>
     *             <li>400 Bad Request with validation messages</li>
     *             <li>500 Internal Server Error if an exception occurs</li>
     *         </ul>
     */
    @PostMapping("/search")
    public ResponseEntity<?> searchFlights(@Valid @RequestBody FlightSearchRequest request,
                                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // Validation failure: return detailed messages
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        try {
            List<Flight> results = flightSearchService.searchFlights(request);
            return ResponseEntity.ok(results); // Success: return results
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred while processing the request.");
        }
    }
}
