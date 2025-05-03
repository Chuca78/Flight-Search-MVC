package com.example.flightsearchmvc.controller;

import com.example.flightsearchmvc.model.Flight;
import com.example.flightsearchmvc.model.FlightSearchRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

/**
 * MVC Controller for handling user-facing flight search interactions.
 *
 * <p>This controller serves the Thymeleaf-based UI. It handles:
 * <ul>
 *   <li>GET "/" — renders the search form</li>
 *   <li>POST "/search" — submits form data to the backend REST API and displays results</li>
 * </ul>
 * </p>
 */
@Controller
public class FlightController {

    private final RestTemplate restTemplate;
    private static final Logger logger = LoggerFactory.getLogger(FlightController.class);

    /**
     * Constructor-based injection for RestTemplate.
     *
     * @param restTemplate the RestTemplate used to call the backend API
     */
    public FlightController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Handles GET request to render the flight search form.
     *
     * @param model Spring model to bind form data
     * @return name of the Thymeleaf view (index.html)
     */
    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("flightSearchRequest", new FlightSearchRequest());
        return "index";
    }

    /**
     * Handles POST request for flight search. Sends request to backend API and displays the results.
     *
     * <p>If the API call fails, an error message is displayed. If no results match,
     * an empty result list is rendered.</p>
     *
     * @param flightSearchRequest form data from user input
     * @param model Spring model to bind results to view
     * @return name of the Thymeleaf view (index.html)
     */
    @PostMapping("/search")
    public String searchFlights(@ModelAttribute FlightSearchRequest flightSearchRequest,
                                Model model) {

        String url = "http://localhost:8080/api/search";
        List<Flight> results = Collections.emptyList();

        try {
            logger.info("Searching flights: {}", flightSearchRequest);
            Flight[] response = restTemplate.postForObject(url, flightSearchRequest, Flight[].class);
            if (response != null) {
                results = List.of(response); // convert array to list
            }
        } catch (RestClientException e) {
            logger.error("Error calling flight API: {}", e.getMessage());
            model.addAttribute("errorMessage", "Could not fetch flight data. Please try again later.");
        }

        // Bind input and results back to the view
        model.addAttribute("flightSearchRequest", flightSearchRequest);
        model.addAttribute("flightResults", results);
        return "index";
    }
}
