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
 * Unit test for {@link FlightSearchService}.
 * Simulates Amadeus API interaction and validates the transformation of API responses
 * into {@link FlightResult} objects, verifying external integration logic.
 */
class FlightSearchServiceTest {

    /**
     * Tests that a valid Amadeus API response is correctly parsed into a list of {@link FlightResult} objects.
     * The method mocks:
     * - OAuth2 token retrieval via the token endpoint
     * - Flight offer JSON response via the flight search endpoint
     */
    @SuppressWarnings("unchecked")
    @Test
    void searchWithAmadeus_returnsMockedFlightResults() {
        // Setup mocked RestTemplate and configuration properties
        RestTemplate mockRestTemplate = Mockito.mock(RestTemplate.class);
        AmadeusProperties mockProps = Mockito.mock(AmadeusProperties.class);

        // Configure stubbed Amadeus API property values
        String mockTokenUrl = "https://test.api.amadeus.com/v1/security/oauth2/token";
        String mockApiUrl = "https://test.api.amadeus.com/v2/shopping/flight-offers";
        String mockAccessToken = "mocked_token";

        when(mockProps.getTokenUrl()).thenReturn(mockTokenUrl);
        when(mockProps.getApiUrl()).thenReturn(mockApiUrl);
        when(mockProps.getClientId()).thenReturn("mock_client_id");
        when(mockProps.getClientSecret()).thenReturn("mock_client_secret");

        // Simulate a valid token response from Amadeus
        Map<String, Object> tokenMap = new HashMap<>();
        tokenMap.put("access_token", mockAccessToken);
        ResponseEntity<Map<String, Object>> tokenResponse = new ResponseEntity<>(tokenMap, HttpStatus.OK);
        when(mockRestTemplate.exchange(
                eq(mockTokenUrl),
                eq(HttpMethod.POST),
                any(HttpEntity.class),
                any(ParameterizedTypeReference.class))
        ).thenReturn(tokenResponse);

        // Simulate a valid flight offer JSON response
        String apiJson = """
        {
          "data": [
            {
              "itineraries": [ {
                "segments": [ {
                  "carrierCode": "UA",
                  "departure": { "at": "2025-05-05T08:00" },
                  "arrival": { "at": "2025-05-05T11:00" }
                }]
              }],
              "price": { "total": "350.00" }
            }
          ]
        }""";
        ResponseEntity<String> apiResponse = new ResponseEntity<>(apiJson, HttpStatus.OK);

        when(mockRestTemplate.exchange(
                startsWith(mockApiUrl),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(String.class),
                anyMap())
        ).thenReturn(apiResponse);

        // Build a mock search request
        FlightSearchRequest request = new FlightSearchRequest();
        request.setOrigin("CID");
        request.setDestination("CLT");
        request.setDate(LocalDate.of(2025, 5, 5));
        request.setPassengers(1);

        // Invoke the search method
        FlightSearchService service = new FlightSearchService(mockRestTemplate, mockProps);
        List<FlightResult> results = service.searchWithAmadeus(request);

        // Validate results
        assertNotNull(results, "Result list should not be null");
        assertFalse(results.isEmpty(), "Expected at least one result");
        assertEquals("UA", results.get(0).getAirline(), "Expected airline to be UA");
        assertEquals("CID", results.get(0).getOrigin(), "Expected origin to match input");
        assertEquals("CLT", results.get(0).getDestination(), "Expected destination to match input");
    }
}
