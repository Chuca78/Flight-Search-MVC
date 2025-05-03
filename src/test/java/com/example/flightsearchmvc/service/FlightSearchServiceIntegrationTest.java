package com.example.flightsearchmvc.service;

import com.example.flightsearchmvc.model.Flight;
import com.example.flightsearchmvc.model.FlightSearchRequest;
import com.example.flightsearchmvc.repository.FlightRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration tests for FlightSearchService using actual Spring context and H2 database.
 *
 * <p>This class verifies the end-to-end behavior of the service with a real database,
 * ensuring that JPA repository and data logic work correctly together.</p>
 */
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class FlightSearchServiceIntegrationTest {

    @Autowired
    private FlightSearchService flightSearchService;

    @Autowired
    private FlightRepository flightRepository;

    /**
     * Preloads the database with a known flight before each test.
     */
    @BeforeEach
    void setUp() {
        flightRepository.deleteAll();

        flightRepository.save(new Flight(
            "New York", "London", LocalDate.of(2025, 5, 1),
            "Test Airline", 300.0,
            LocalTime.of(7, 0), LocalTime.of(19, 30)
        ));
    }

    /**
     * Test that verifies valid request returns matching flights from the database.
     */
    @Test
    void searchFlights_returnsResultsForValidRequest() {
        FlightSearchRequest request = new FlightSearchRequest();
        request.setOrigin("New York");
        request.setDestination("London");
        request.setDate(LocalDate.of(2025, 5, 1));
        request.setPassengers(1);

        List<Flight> results = flightSearchService.searchFlights(request);

        assertNotNull(results);
        assertFalse(results.isEmpty());
        assertEquals("London", results.get(0).getDestination());
    }

    /**
     * Test that verifies request returns empty list when no flight matches are found.
     */
    @Test
    void searchFlights_returnsEmptyForNoMatch() {
        FlightSearchRequest request = new FlightSearchRequest();
        request.setOrigin("Nowhere");
        request.setDestination("Atlantis");
        request.setDate(LocalDate.of(2025, 5, 1));
        request.setPassengers(1);

        List<Flight> results = flightSearchService.searchFlights(request);

        assertNotNull(results);
        assertTrue(results.isEmpty());
    }
}
