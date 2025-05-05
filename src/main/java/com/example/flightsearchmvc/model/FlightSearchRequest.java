package com.example.flightsearchmvc.model;

import java.time.LocalDate;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * Represents the user input for searching flights.
 * This model is bound to the flight search form.
 */
public class FlightSearchRequest {

    @NotNull(message = "Origin is required")
    private String origin;  // Departure airport or city

    @NotNull(message = "Destination is required")
    private String destination;  // Arrival airport or city

    @NotNull(message = "Date is required")
    private LocalDate date;  // Travel date

    @Min(value = 1, message = "Passengers must be at least 1")
    private int passengers;  // Number of passengers

    /**
     * Default constructor used by frameworks.
     */
    public FlightSearchRequest() {
    }

    // Getters and setters

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getPassengers() {
        return passengers;
    }

    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }
}
