package com.example.flightsearchmvc.model;

/**
 * Represents the result of a flight search.
 * This model holds relevant details about a flight such as airline, route, times, and price.
 */
public class FlightResult {

    private String airline;         // Airline name or carrier code
    private String origin;          // Origin airport or city code
    private String destination;     // Destination airport or city code
    private String departureTime;   // Scheduled departure time
    private String arrivalTime;     // Scheduled arrival time
    private double price;           // Ticket price in USD

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

    // Getters and setters

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
}
