package com.example.flightsearchmvc.service;

import com.example.flightsearchmvc.config.AmadeusProperties;
import com.example.flightsearchmvc.model.FlightResult;
import com.example.flightsearchmvc.model.FlightSearchRequest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

/**
 * Unit test for the FlightSearchService class,
 * specifically testing the integration with the mocked Amadeus API.
 */
class FlightSearchServiceTest {

    /**
     * Tests that searchWithAmadeus() correctly parses a mocked Amadeus API response
     * and returns expected flight results.
     */
    @SuppressWarnings("unchecked")
    @Test
    void searchWithAmadeus_returnsMockedFlightResults() {
        // Mock the external RestTemplate and Amadeus config properties
        RestTemplate mockRestTemplate = Mockito.mock(RestTemplate.class);
        AmadeusProperties mockProps = Mockito.mock(AmadeusProperties.class);

        // Set up expected property values
        String mockTokenUrl = "https://test.api.amadeus.com/v1/security/oauth2/token";
        String mockApiUrl = "https://test.api.amadeus.com/v2/shopping/flight-offers";
        String mockAccessToken = "mocked_token";

        // Configure mocked properties to return dummy credentials and URLs
        when(mockProps.getTokenUrl()).thenReturn(mockTokenUrl);
        when(mockProps.getClientId()).thenReturn("mock_client_id");
        when(mockProps.getClientSecret()).thenReturn("mock_client_secret");

        // Simulate a successful token response
        Map<String, Object> tokenMap = new HashMap<>();
        tokenMap.put("access_token", mockAccessToken);
        ResponseEntity<Map<String, Object>> tokenResponse = new ResponseEntity<>(tokenMap, HttpStatus.OK);
        when(mockRestTemplate.exchange(
                eq(mockTokenUrl),
                eq(HttpMethod.POST),
                any(HttpEntity.class),
                any(ParameterizedTypeReference.class)
        )).thenReturn(tokenResponse);

        // Simulate a successful flight offer response from Amadeus
        String apiJson = """
        {
          "data": [
            {
              "itineraries": [{
                "segments": [{
                  "carrierCode": "UA",
                  "departure": {"at": "2025-05-05T08:00"},
                  "arrival": {"at": "2025-05-05T11:00"}
                }]
              }],
              "price": {
                "total": "350.00"
              }
            }
          ]
        }""";

        ResponseEntity<String> apiResponse = new ResponseEntity<>(apiJson, HttpStatus.OK);
        when(mockRestTemplate.exchange(
                startsWith(mockApiUrl),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(String.class),
                anyMap()
        )).thenReturn(apiResponse);

        // Construct the search request
        FlightSearchRequest request = new FlightSearchRequest();
        request.setOrigin("CID");
        request.setDestination("CLT");
        request.setDate(LocalDate.of(2025, 5, 5));
        request.setPassengers(1);

        // Initialize service with mocked dependencies
        FlightSearchService service = new FlightSearchService(mockRestTemplate, mockProps);

        // Perform the search using the mocked Amadeus API
        List<FlightResult> results = service.searchWithAmadeus(request);

        // Validate the results
        assertNotNull(results, "Results list should not be null");
        assertFalse(results.isEmpty(), "Results list should not be empty");
        assertEquals("UA", results.get(0).getAirline(), "Airline code should match mock data");
        assertEquals("CID", results.get(0).getOrigin(), "Origin should match request");
        assertEquals("CLT", results.get(0).getDestination(), "Destination should match request");
    }
}
