package com.example.flightsearchmvc.model;

import com.example.flightsearchmvc.util.AirlineUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents the result of a flight search.
 * This model holds relevant details about a flight such as airline, route, times, and price.
 */
public class FlightResult {

    private String airline;
    private String origin;
    private String destination;
    private String departureTime;
    private String arrivalTime;
    private double price;

    /**
     * Default constructor for frameworks and serialization.
     */
    public FlightResult() {
    }

    /**
     * Constructs a flight result with all fields populated.
     *
     * @param airline        Airline or carrier code
     * @param origin         Departure location
     * @param destination    Arrival location
     * @param departureTime  Flight departure time
     * @param arrivalTime    Flight arrival time
     * @param price          Flight ticket price
     */
    public FlightResult(String airline, String origin, String destination, String departureTime, String arrivalTime, double price) {
        this.airline = airline;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.price = price;
    }

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

    // === Presentation Helpers ===

    public String getFormattedAirline() {
        return AirlineUtils.getAirlineName(airline);
    }

    public String getFormattedOrigin() {
        return origin != null ? origin.toUpperCase() : "";
    }

    public String getFormattedDestination() {
        return destination != null ? destination.toUpperCase() : "";
    }

    public String getFormattedDepartureTime() {
        return formatDateTime(departureTime);
    }

    public String getFormattedArrivalTime() {
        return formatDateTime(arrivalTime);
    }

    private String formatDateTime(String rawDateTime) {
        try {
            LocalDateTime dt = LocalDateTime.parse(rawDateTime);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy – hh:mm a");
            return dt.format(formatter);
        } catch (Exception e) {
            return rawDateTime; // fallback
        }
    }
}
