package com.example.flightsearchmvc.repository;

import com.example.flightsearchmvc.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for accessing booking data.
 * Inherits CRUD methods from JpaRepository and adds custom queries.
 */
public interface BookingRepository extends JpaRepository<Booking, Long> {

    /**
     * Retrieves all bookings made by a specific user.
     *
     * @param username the username associated with the booking
     * @return list of matching Booking records
     */
    List<Booking> findByUsername(String username);
}
