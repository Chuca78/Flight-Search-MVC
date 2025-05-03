package com.example.flightsearchmvc.repository;

import com.example.flightsearchmvc.model.Flight;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration tests for the FlightRepository.
 *
 * <p>Validates that the custom query method correctly returns flights
 * based on origin, destination, and date filters.</p>
 */
@DataJpaTest
class FlightRepositoryTest {

    @Autowired
    private FlightRepository flightRepository;

    /**
     * Ensures that a flight saved to the repository can be retrieved
     * using origin, destination, and date filters.
     */
    @Test
    @DisplayName("Should return flights matching origin, destination, and date")
    void findByOriginAndDestinationAndDate_shouldReturnMatches() {
        // Arrange
        Flight flight = new Flight(
                "New York",
                "London",
                LocalDate.of(2025, 5, 1),
                "Test Airline",
                450.00,
                LocalTime.of(8, 30),
                LocalTime.of(20, 0)
        );
        flightRepository.save(flight);

        // Act
        List<Flight> results = flightRepository.findByOriginAndDestinationAndDate(
                "New York", "London", LocalDate.of(2025, 5, 1)
        );

        // Assert
        assertEquals(1, results.size());
        assertEquals("Test Airline", results.get(0).getAirline());
    }

    /**
     * Ensures that the repository returns an empty list when no flights match the query.
     */
    @Test
    @DisplayName("Should return empty list if no flights match search criteria")
    void findByOriginAndDestinationAndDate_shouldReturnEmptyWhenNoMatch() {
        List<Flight> results = flightRepository.findByOriginAndDestinationAndDate(
                "Nowhere", "Atlantis", LocalDate.of(2025, 12, 25)
        );
        assertTrue(results.isEmpty());
    }
}
