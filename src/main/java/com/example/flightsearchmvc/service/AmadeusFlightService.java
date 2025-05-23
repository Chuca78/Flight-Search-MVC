package com.example.flightsearchmvc.service;

import com.example.flightsearchmvc.config.AmadeusProperties;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @deprecated This service was originally used to handle authentication and
 * API calls to the Amadeus flight search system, but is no longer active.
 * Functionality has been fully consolidated into {@link FlightSearchService}.
 *
 * This class is retained only for backup and reference purposes.
 * It is currently not used or injected anywhere in the application.
 */
@Deprecated
@Service
public class AmadeusFlightService {

    private final RestTemplate restTemplate;
    private final AmadeusProperties amadeusProperties;

    /**
     * Constructor for injecting configuration and HTTP client.
     *
     * @param restTemplate       HTTP client for REST calls
     * @param amadeusProperties  Configuration properties for Amadeus credentials
     */
    public AmadeusFlightService(RestTemplate restTemplate, AmadeusProperties amadeusProperties) {
        this.restTemplate = restTemplate;
        this.amadeusProperties = amadeusProperties;
    }

    /**
     * Retrieves an OAuth2 bearer token from the Amadeus token endpoint.
     * 
     * @return a valid access token string
     * @throws IllegalStateException if token response is invalid
     */
    public String getAccessToken() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        String body = "grant_type=client_credentials"
                + "&client_id=" + amadeusProperties.getClientId()
                + "&client_secret=" + amadeusProperties.getClientSecret();

        HttpEntity<String> request = new HttpEntity<>(body, headers);

        ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                amadeusProperties.getTokenUrl(),
                HttpMethod.POST,
                request,
                new ParameterizedTypeReference<>() {}
        );

        Map<String, Object> responseBody = response.getBody();
        if (responseBody == null || !responseBody.containsKey("access_token")) {
            throw new IllegalStateException("Failed to retrieve access token from Amadeus.");
        }

        return responseBody.get("access_token").toString();
    }

    /**
     * Makes a flight search request to the Amadeus API with minimal parameters.
     *
     * @param origin        IATA airport code for departure
     * @param destination   IATA airport code for arrival
     * @param departureDate Travel date (YYYY-MM-DD)
     * @return JSON response from the API as a string
     * @throws RuntimeException if the request fails
     */
    public String searchFlights(String origin, String destination, String departureDate) {
        String token = getAccessToken();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setAccept(java.util.List.of(MediaType.APPLICATION_JSON));

        String url = amadeusProperties.getApiUrl()
                + "?originLocationCode=" + origin
                + "&destinationLocationCode=" + destination
                + "&departureDate=" + departureDate
                + "&adults=1";

        HttpEntity<Void> request = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                request,
                String.class
        );

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            throw new RuntimeException("Failed to fetch flight offers");
        }
    }
}
