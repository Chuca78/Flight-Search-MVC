package com.example.flightsearchmvc.model;

import java.time.LocalDate;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * Represents the user input for a flight search request.
 * This model is bound to the flight search form via Thymeleaf and used in both MVC and REST layers.
 */
public class FlightSearchRequest {

    @NotNull(message = "Origin is required")
    private String origin;

    @NotNull(message = "Destination is required")
    private String destination;

    @NotNull(message = "Date is required")
    private LocalDate date;

    @Min(value = 1, message = "Passengers must be at least 1")
    private int passengers;

    /**
     * Default constructor for framework use.
     */
    public FlightSearchRequest() {
    }

    /**
     * Returns the origin airport IATA code.
     * @return origin code (e.g., "JFK")
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * Sets the origin airport code. Converts input to uppercase for consistency.
     * @param origin airport code string
     */
    public void setOrigin(String origin) {
        this.origin = origin != null ? origin.toUpperCase() : null;
    }

    /**
     * Returns the destination airport IATA code.
     * @return destination code (e.g., "LAX")
     */
    public String getDestination() {
        return destination;
    }

    /**
     * Sets the destination airport code. Converts input to uppercase for consistency.
     * @param destination airport code string
     */
    public void setDestination(String destination) {
        this.destination = destination != null ? destination.toUpperCase() : null;
    }

    /**
     * Returns the selected departure date.
     * @return travel date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Sets the departure date.
     * @param date LocalDate object
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Returns the number of passengers in the search request.
     * @return number of travelers
     */
    public int getPassengers() {
        return passengers;
    }

    /**
     * Sets the number of passengers.
     * @param passengers integer value (minimum 1)
     */
    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }
}
