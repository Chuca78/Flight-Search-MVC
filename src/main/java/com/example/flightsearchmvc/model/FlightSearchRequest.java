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
    private String origin;

    @NotNull(message = "Destination is required")
    private String destination;

    @NotNull(message = "Date is required")
    private LocalDate date;

    @Min(value = 1, message = "Passengers must be at least 1")
    private int passengers;

    /**
     * Default constructor used by frameworks.
     */
    public FlightSearchRequest() {
    }

    /**
     * Gets the origin airport or city.
     * @return origin code
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * Sets the origin airport or city.
     * @param origin origin code
     */
    public void setOrigin(String origin) {
        this.origin = origin != null ? origin.toUpperCase() : null;
    }

    /**
     * Gets the destination airport or city.
     * @return destination code
     */
    public String getDestination() {
        return destination;
    }

    /**
     * Sets the destination airport or city.
     * @param destination destination code
     */
    public void setDestination(String destination) {
        this.destination = destination != null ? destination.toUpperCase() : null;
    }

    /**
     * Gets the selected travel date.
     * @return travel date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Sets the selected travel date.
     * @param date travel date
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Gets the number of passengers.
     * @return number of passengers
     */
    public int getPassengers() {
        return passengers;
    }

    /**
     * Sets the number of passengers.
     * @param passengers passenger count
     */
    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }
}
