package com.example.flightsearchmvc.controller;

import com.example.flightsearchmvc.model.FlightSearchRequest;
import com.example.flightsearchmvc.model.FlightResult;
import com.example.flightsearchmvc.service.FlightSearchService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Web controller that handles requests for flight search using a traditional HTML-based interface.
 * Provides endpoints for rendering the search form and processing user submissions.
 */
@Controller
public class FlightController {

    private final FlightSearchService flightSearchService;

    /**
     * Constructor for injecting the flight search service.
     *
     * @param flightSearchService Service for retrieving flight information
     */
    public FlightController(FlightSearchService flightSearchService) {
        this.flightSearchService = flightSearchService;
    }

    /**
     * Handles GET requests to the root path ("/") and displays the search form.
     *
     * @param model Spring model to bind the form object
     * @return View name for the index page
     */
    @GetMapping("/")
    public String showSearchForm(Model model) {
        // Initialize a new form object to bind user input
        model.addAttribute("flightSearchRequest", new FlightSearchRequest());
        return "search";
    }

    /**
     * Handles POST requests to perform a flight search.
     *
     * @param request        The flight search form data
     * @param bindingResult  Holds validation errors, if any
     * @param model          Spring model to pass attributes to the view
     * @return View name to render results or return to form on error
     */
    @PostMapping("/search")
    public String processSearch(
            @ModelAttribute("flightSearchRequest") @Valid FlightSearchRequest request,
            BindingResult bindingResult,
            Model model
    ) {
        // Return to form if validation errors exist
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "Invalid search input. Please check all fields.");
            return "search";
        }

        try {
            // Perform flight search using Amadeus API
            List<FlightResult> results = flightSearchService.searchWithAmadeus(request);

            // Add results to model for rendering
            model.addAttribute("flightResults", results);
        } catch (Exception e) {
            // Display error message if search fails
            model.addAttribute("error", "Unable to fetch flight data: " + e.getMessage());
        }

        return "search";
    }
}
