package com.example.flightsearchmvc.repository;

import com.example.flightsearchmvc.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Spring Data JPA repository for accessing {@link Booking} entities.
 * Provides basic CRUD operations and custom query methods.
 */
public interface BookingRepository extends JpaRepository<Booking, Long> {

    /**
     * Finds all bookings associated with the specified username.
     *
     * @param username the username to search for
     * @return a list of {@link Booking} objects belonging to the user
     */
    List<Booking> findByUsername(String username);
}
