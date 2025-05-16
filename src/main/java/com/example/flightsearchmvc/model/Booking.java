package com.example.flightsearchmvc.model;

import jakarta.persistence.*;
import java.time.LocalDate;

/**
 * Entity class representing a flight booking.
 * Used for persisting booking information in the H2 database.
 */
@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String airline;
    private String origin;
    private String destination;
    private LocalDate date;
    private String departureTime;
    private String arrivalTime;
    private double price;

    /**
     * Gets the unique booking ID.
     * @return booking ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique booking ID.
     * @param id booking ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the username associated with the booking.
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username associated with the booking.
     * @param username the user's name
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the airline for the booking.
     * @return airline name
     */
    public String getAirline() {
        return airline;
    }

    /**
     * Sets the airline for the booking.
     * @param airline airline name
     */
    public void setAirline(String airline) {
        this.airline = airline;
    }

    /**
     * Gets the origin airport code.
     * @return origin code
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * Sets the origin airport code.
     * @param origin origin code
     */
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    /**
     * Gets the destination airport code.
     * @return destination code
     */
    public String getDestination() {
        return destination;
    }

    /**
     * Sets the destination airport code.
     * @param destination destination code
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }

    /**
     * Gets the booking date.
     * @return date of booking
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Sets the booking date.
     * @param date booking date
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Gets the flight departure time.
     * @return departure time
     */
    public String getDepartureTime() {
        return departureTime;
    }

    /**
     * Sets the flight departure time.
     * @param departureTime departure time
     */
    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    /**
     * Gets the flight arrival time.
     * @return arrival time
     */
    public String getArrivalTime() {
        return arrivalTime;
    }

    /**
     * Sets the flight arrival time.
     * @param arrivalTime arrival time
     */
    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    /**
     * Gets the flight price.
     * @return flight price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the flight price.
     * @param price flight price
     */
    public void setPrice(double price) {
        this.price = price;
    }
}
