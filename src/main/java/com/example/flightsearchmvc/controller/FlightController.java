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
 * Web controller for handling flight search via a traditional MVC interface.
 * Renders the search form and processes flight search requests using Amadeus API.
 */
@Controller
public class FlightController {

    private final FlightSearchService flightSearchService;

    /**
     * Constructor for injecting the flight search service.
     *
     * @param flightSearchService Service responsible for retrieving flights
     */
    public FlightController(FlightSearchService flightSearchService) {
        this.flightSearchService = flightSearchService;
    }

    /**
     * Displays the main flight search form at the root URL ("/").
     *
     * @param model Spring model used to bind form input
     * @return View name for the search page
     */
    @GetMapping("/")
    public String showSearchForm(Model model) {
        model.addAttribute("flightSearchRequest", new FlightSearchRequest());
        return "search";
    }

    /**
     * Processes the submitted flight search form.
     * Validates user input, performs an API search, and displays results or errors.
     *
     * @param request       User's search parameters
     * @param bindingResult Validation result for the request
     * @param model         Spring model to pass search results or errors
     * @return View name for the results page or back to form with error
     */
    @PostMapping("/search")
    public String processSearch(
            @ModelAttribute("flightSearchRequest") @Valid FlightSearchRequest request,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "Invalid search input. Please check all fields.");
            return "search";
        }

        try {
            List<FlightResult> results = flightSearchService.searchWithAmadeus(request);
            model.addAttribute("flightResults", results);
        } catch (IllegalArgumentException iae) {
            model.addAttribute("error", iae.getMessage()); // Specific user input issue
        } catch (Exception e) {
            String errorMessage = e.getMessage();
            if (errorMessage != null && errorMessage.contains("INVALID DATE")) {
                model.addAttribute("error", "Departure date must be in the future. Please select a valid travel date.");
            } else {
                model.addAttribute("error", "Unable to fetch flight data. Please check your input or try again later.");
            }
        }

        return "search";
    }
}
