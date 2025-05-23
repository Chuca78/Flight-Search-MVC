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
     * @param username user identifier
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the airline code.
     * @return airline code (e.g., UA, DL)
     */
    public String getAirline() {
        return airline;
    }

    /**
     * Sets the airline code.
     * @param airline airline code
     */
    public void setAirline(String airline) {
        this.airline = airline;
    }

    /**
     * Gets the origin airport code.
     * @return origin IATA code
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * Sets the origin airport code.
     * Converts to uppercase for consistency.
     * @param origin origin IATA code
     */
    public void setOrigin(String origin) {
        this.origin = origin != null ? origin.toUpperCase() : null;
    }

    /**
     * Gets the destination airport code.
     * @return destination IATA code
     */
    public String getDestination() {
        return destination;
    }

    /**
     * Sets the destination airport code.
     * Converts to uppercase for consistency.
     * @param destination destination IATA code
     */
    public void setDestination(String destination) {
        this.destination = destination != null ? destination.toUpperCase() : null;
    }

    /**
     * Gets the booking date.
     * @return local date of booking
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Sets the booking date.
     * @param date local date
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Gets the ISO 8601 departure timestamp string.
     * @return departure timestamp
     */
    public String getDepartureTime() {
        return departureTime;
    }

    /**
     * Sets the departure timestamp.
     * @param departureTime ISO 8601 string
     */
    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    /**
     * Gets the ISO 8601 arrival timestamp string.
     * @return arrival timestamp
     */
    public String getArrivalTime() {
        return arrivalTime;
    }

    /**
     * Sets the arrival timestamp.
     * @param arrivalTime ISO 8601 string
     */
    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    /**
     * Gets the total price for this booking.
     * @return total price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the total price.
     * @param price total booking cost
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Gets the number of passengers.
     * @return passenger count
     */
    public int getPassengers() {
        return passengers;
    }

    /**
     * Sets the number of passengers.
     * @param passengers count
     */
    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }

    // === Presentation Helpers ===

    /**
     * Returns the full airline name using airline code mapping.
     * @return full airline name
     */
    public String getFormattedAirline() {
        return com.example.flightsearchmvc.util.AirlineUtils.getAirlineName(airline);
    }

    /**
     * Returns a formatted display string for departure time.
     * @return formatted time or fallback
     */
    public String getFormattedDepartureTime() {
        return formatDateTime(departureTime);
    }

    /**
     * Returns a formatted display string for arrival time.
     * @return formatted time or fallback
     */
    public String getFormattedArrivalTime() {
        return formatDateTime(arrivalTime);
    }

    /**
     * Converts an ISO 8601 date-time string to a readable format.
     * @param raw ISO 8601 date-time string
     * @return Formatted string like "May 21, 2025 – 07:15 AM", or fallback
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
