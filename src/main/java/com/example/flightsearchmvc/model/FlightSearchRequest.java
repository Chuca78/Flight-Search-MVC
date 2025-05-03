package com.example.flightsearchmvc.model;

import java.time.LocalDate;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * Model representing a user's flight search request.
 *
 * <p>This class is used for both the REST API input and Thymeleaf form binding.
 * It includes validation annotations to ensure proper input for origin, destination,
 * travel date, and passenger count.</p>
 */
public class FlightSearchRequest {

    /** City or airport of departure (must not be null) */
    @NotNull(message = "Origin is required")
    private String origin;

    /** City or airport of arrival (must not be null) */
    @NotNull(message = "Destination is required")
    private String destination;

    /** Travel date (must not be null) */
    @NotNull(message = "Date is required")
    private LocalDate date;

    /** Passenger count (minimum 1 required) */
    @Min(value = 1, message = "Passengers must be at least 1")
    private int passengers;

    /**
     * Default constructor required for Spring binding and form usage.
     */
    public FlightSearchRequest() {}

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
