package com.example.flightsearchmvc.model;

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

    /**
     * Gets the airline.
     * @return airline name
     */
    public String getAirline() {
        return airline;
    }

    /**
     * Sets the airline.
     * @param airline airline name
     */
    public void setAirline(String airline) {
        this.airline = airline;
    }

    /**
     * Gets the origin.
     * @return origin location
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * Sets the origin.
     * @param origin origin location
     */
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    /**
     * Gets the destination.
     * @return destination location
     */
    public String getDestination() {
        return destination;
    }

    /**
     * Sets the destination.
     * @param destination destination location
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }

    /**
     * Gets the departure time.
     * @return departure time
     */
    public String getDepartureTime() {
        return departureTime;
    }

    /**
     * Sets the departure time.
     * @param departureTime departure time
     */
    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    /**
     * Gets the arrival time.
     * @return arrival time
     */
    public String getArrivalTime() {
        return arrivalTime;
    }

    /**
     * Sets the arrival time.
     * @param arrivalTime arrival time
     */
    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    /**
     * Gets the flight price.
     * @return ticket price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the flight price.
     * @param price ticket price
     */
    public void setPrice(double price) {
        this.price = price;
    }
}
