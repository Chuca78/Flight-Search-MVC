package com.example.flightsearchmvc.model;

import com.example.flightsearchmvc.util.AirlineUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents the result of a flight search.
 * Holds flight details such as airline, origin, destination, departure/arrival times, and ticket price.
 * Used for both display in the UI and data transfer between services.
 */
public class FlightResult {

    private String airline;
    private String origin;
    private String destination;
    private String departureTime;
    private String arrivalTime;
    private double price;

    /**
     * Default no-argument constructor required for frameworks and serialization.
     */
    public FlightResult() {
    }

    /**
     * Constructs a complete flight result object.
     *
     * @param airline        Airline IATA code (e.g., "UA", "DL")
     * @param origin         Origin airport code
     * @param destination    Destination airport code
     * @param departureTime  Departure time (ISO 8601 string)
     * @param arrivalTime    Arrival time (ISO 8601 string)
     * @param price          Base price for a single passenger
     */
    public FlightResult(String airline, String origin, String destination,
                        String departureTime, String arrivalTime, double price) {
        this.airline = airline;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.price = price;
    }

    // === Raw field accessors and mutators ===

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

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

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // === UI display helpers ===

    /**
     * Gets the full airline name based on the airline code.
     * @return airline name (e.g., "United Airlines")
     */
    public String getFormattedAirline() {
        return AirlineUtils.getAirlineName(airline);
    }

    /**
     * Returns the uppercase IATA origin code.
     * @return origin in uppercase
     */
    public String getFormattedOrigin() {
        return origin != null ? origin.toUpperCase() : "";
    }

    /**
     * Returns the uppercase IATA destination code.
     * @return destination in uppercase
     */
    public String getFormattedDestination() {
        return destination != null ? destination.toUpperCase() : "";
    }

    /**
     * Formats the ISO departure time string for display.
     * @return formatted departure time (e.g., "May 23, 2025 – 09:45 AM")
     */
    public String getFormattedDepartureTime() {
        return formatDateTime(departureTime);
    }

    /**
     * Formats the ISO arrival time string for display.
     * @return formatted arrival time (e.g., "May 23, 2025 – 12:10 PM")
     */
    public String getFormattedArrivalTime() {
        return formatDateTime(arrivalTime);
    }

    /**
     * Converts an ISO 8601 timestamp to a friendly display format.
     * If parsing fails, returns the original string.
     *
     * @param rawDateTime ISO 8601 date-time string
     * @return formatted string or raw input as fallback
     */
    private String formatDateTime(String rawDateTime) {
        try {
            LocalDateTime dt = LocalDateTime.parse(rawDateTime);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy – hh:mm a");
            return dt.format(formatter);
        } catch (Exception e) {
            return rawDateTime;
        }
    }
}
