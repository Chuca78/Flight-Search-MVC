package com.example.flightsearchmvc.service;

import com.example.flightsearchmvc.model.Flight;
import com.example.flightsearchmvc.model.FlightSearchRequest;
import com.example.flightsearchmvc.repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service layer responsible for processing flight search requests.
 *
 * <p>Uses the FlightRepository to query the database for flights
 * that match the origin, destination, and travel date provided by the user.</p>
 */
@Service
public class FlightSearchService {

    private final FlightRepository flightRepository;

    /**
     * Constructor for dependency injection of the flight repository.
     *
     * @param flightRepository Spring Data JPA repository for flight entities
     */
    public FlightSearchService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    /**
     * Searches for flights based on the user's input.
     *
     * @param request the flight search request including origin, destination, and date
     * @return a list of flights matching the criteria
     */
    public List<Flight> searchFlights(FlightSearchRequest request) {
        return flightRepository.findByOriginAndDestinationAndDate(
            capitalize(request.getOrigin()),
            capitalize(request.getDestination()),
            request.getDate()
        );
    }

    /**
     * Capitalizes the first letter of the input string and lowercases the rest.
     * Useful for consistent query matching and formatting.
     *
     * @param input the input string to normalize
     * @return the capitalized version or an empty string if input is null/blank
     */
    // TODO: Consider moving to a shared StringUtils class if reused elsewhere
    private String capitalize(String input) {
        if (input == null || input.isBlank()) return "";
        input = input.trim();
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }
}
