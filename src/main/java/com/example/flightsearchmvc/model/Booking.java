package com.example.flightsearchmvc.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Entity class representing a flight booking.
 * Used for persisting booking information in the H2 database.
 */
@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String airline;

    @Column(nullable = false)
    private String origin;

    @Column(nullable = false)
    private String destination;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private String departureTime;

    @Column(nullable = false)
    private String arrivalTime;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private int passengers;

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
        this.origin = origin != null ? origin.toUpperCase() : null;
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
        this.destination = destination != null ? destination.toUpperCase() : null;
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

    /**
     * Gets the number of passengers for this booking.
     * @return passenger count
     */
    public int getPassengers() {
        return passengers;
    }

    /**
     * Sets the number of passengers for this booking.
     * @param passengers passenger count
     */
    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }

    /**
     * Gets the full airline name for display based on the airline code.
     * @return formatted airline name
     */
    public String getFormattedAirline() {
        return com.example.flightsearchmvc.util.AirlineUtils.getAirlineName(airline);
    }

    /**
     * Gets the formatted departure time for display.
     * @return formatted departure time string
     */
    public String getFormattedDepartureTime() {
        return formatDateTime(departureTime);
    }

    /**
     * Gets the formatted arrival time for display.
     * @return formatted arrival time string
     */
    public String getFormattedArrivalTime() {
        return formatDateTime(arrivalTime);
    }

    /**
     * Converts an ISO date-time string to a user-friendly format.
     * @param raw raw ISO 8601 date-time string
     * @return formatted date-time string, or raw if parsing fails
     */
    private String formatDateTime(String raw) {
        try {
            LocalDateTime dt = LocalDateTime.parse(raw);
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MMM dd, yyyy – hh:mm a");
            return dt.format(fmt);
        } catch (Exception e) {
            return raw;
        }
    }
}
