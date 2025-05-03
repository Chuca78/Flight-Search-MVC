package com.example.flightsearchmvc.service;

import com.example.flightsearchmvc.model.Flight;
import com.example.flightsearchmvc.model.FlightSearchRequest;
import com.example.flightsearchmvc.repository.FlightRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the FlightSearchService.
 *
 * Uses Mockito to mock the FlightRepository and isolate service logic.
 */
class FlightSearchServiceTest {

    /**
     * Test that verifies valid search request returns expected flight results.
     */
    @Test
    void searchFlights_returnsResultsForValidRequest() {
        // Arrange
        FlightRepository mockRepo = mock(FlightRepository.class);
        FlightSearchService service = new FlightSearchService(mockRepo);

        FlightSearchRequest request = new FlightSearchRequest();
        request.setOrigin("New York");
        request.setDestination("London");
        request.setDate(LocalDate.of(2025, 5, 1));

        List<Flight> mockFlights = List.of(new Flight(
            "New York", "London", request.getDate(), "TestAir", 250.0,
            LocalTime.of(7, 0), LocalTime.of(19, 0)
        ));

        when(mockRepo.findByOriginAndDestinationAndDate("New York", "London", request.getDate()))
            .thenReturn(mockFlights);

        // Act
        List<Flight> results = service.searchFlights(request);

        // Assert
        assertNotNull(results);
        assertEquals(1, results.size());
        assertEquals("London", results.get(0).getDestination());
    }

    /**
     * Test that verifies service returns an empty list when no flights match the request.
     */
    @Test
    void searchFlights_returnsEmptyForNoMatch() {
        // Arrange
        FlightRepository mockRepo = mock(FlightRepository.class);
        FlightSearchService service = new FlightSearchService(mockRepo);

        FlightSearchRequest request = new FlightSearchRequest();
        request.setOrigin("Nowhere");
        request.setDestination("Atlantis");
        request.setDate(LocalDate.of(2025, 5, 1));

        when(mockRepo.findByOriginAndDestinationAndDate("Nowhere", "Atlantis", request.getDate()))
            .thenReturn(List.of());

        // Act
        List<Flight> results = service.searchFlights(request);

        // Assert
        assertNotNull(results);
        assertTrue(results.isEmpty());
    }
}
