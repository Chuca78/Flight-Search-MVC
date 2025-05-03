package com.example.flightsearchmvc.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Entity representing a flight record in the application.
 *
 * <p>Mapped to a relational database table via Spring Data JPA and used to store
 * flight search and booking data such as origin, destination, times, and price.</p>
 */
@Entity
public class Flight {

    /** Unique flight identifier (primary key) */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** City or airport of origin */
    private String origin;

    /** City or airport of destination */
    private String destination;

    /** Scheduled departure time */
    private LocalTime departureTime;

    /** Scheduled arrival time */
    private LocalTime arrivalTime;

    /** Date of flight */
    private LocalDate date;

    /** Airline operating the flight */
    private String airline;

    /** Ticket price in USD */
    private double price;

    /**
     * Default constructor required by JPA.
     */
    public Flight() {}

    /**
     * Parameterized constructor for creating a complete flight object.
     *
     * @param origin        city or airport of departure
     * @param destination   city or airport of arrival
     * @param date          date of travel
     * @param airline       airline name
     * @param price         ticket price
     * @param departureTime scheduled departure time
     * @param arrivalTime   scheduled arrival time
     */
    public Flight(String origin, String destination, LocalDate date, String airline, double price,
                  LocalTime departureTime, LocalTime arrivalTime) {
        this.origin = origin;
        this.destination = destination;
        this.date = date;
        this.airline = airline;
        this.price = price;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
