package com.example.flightsearchmvc.repository;

import com.example.flightsearchmvc.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Repository interface for accessing flight data.
 *
 * <p>Extends JpaRepository to provide standard CRUD operations.
 * Defines a custom query method to retrieve flights matching origin,
 * destination, and date criteria.</p>
 */
@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    /**
     * Finds all flights that match the given origin, destination, and date.
     *
     * @param origin      the city or airport of departure
     * @param destination the city or airport of arrival
     * @param date        the date of travel
     * @return list of matching Flight entities
     */
    List<Flight> findByOriginAndDestinationAndDate(String origin, String destination, LocalDate date);
}
