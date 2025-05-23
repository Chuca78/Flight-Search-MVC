package com.example.flightsearchmvc.service;

import com.example.flightsearchmvc.config.AmadeusProperties;
import com.example.flightsearchmvc.model.FlightResult;
import com.example.flightsearchmvc.model.FlightSearchRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * Main service layer responsible for flight search logic.
 * Interfaces with the Amadeus API for live data or returns fallback results for testing.
 */
@Service
public class FlightSearchService {

    private final RestTemplate restTemplate;
    private final AmadeusProperties amadeusProperties;

    /**
     * Constructor for dependency injection.
     *
     * @param restTemplate       Spring RestTemplate used for HTTP communication
     * @param amadeusProperties  Configuration containing Amadeus API credentials and URLs
     */
    public FlightSearchService(RestTemplate restTemplate, AmadeusProperties amadeusProperties) {
        this.restTemplate = restTemplate;
        this.amadeusProperties = amadeusProperties;
    }

    /**
     * Performs a live flight search using the Amadeus API based on user input.
     *
     * @param request user search parameters including origin, destination, and date
     * @return list of matching {@link FlightResult} objects
     * @throws IllegalArgumentException if origin/destination IATA codes are invalid
     * @throws RuntimeException if API response is malformed or cannot be parsed
     */
    public List<FlightResult> searchWithAmadeus(FlightSearchRequest request) {
        String origin = request.getOrigin();
        String destination = request.getDestination();

        // Validate that both codes are 3-letter uppercase IATA codes
        if (!origin.matches("^[A-Z]{3}$") || !destination.matches("^[A-Z]{3}$")) {
            throw new IllegalArgumentException("Please enter valid 3-letter airport codes (e.g., JFK, LAX).");
        }

        String token = getAccessToken();

        // Construct API URL with placeholders
        String apiUrl = "https://test.api.amadeus.com/v2/shopping/flight-offers"
                + "?originLocationCode={origin}"
                + "&destinationLocationCode={destination}"
                + "&departureDate={date}"
                + "&adults=1&max=10"; // increased from 5 to 10

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        Map<String, String> uriParams = Map.of(
                "origin", origin,
                "destination", destination,
                "date", request.getDate().toString()
        );

        ResponseEntity<String> response = restTemplate.exchange(
                apiUrl,
                HttpMethod.GET,
                entity,
                String.class,
                uriParams
        );

        List<FlightResult> results = new ArrayList<>();

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(response.getBody());

                // Check for error node
                if (root.has("errors")) {
                    String message = root.path("errors").get(0).path("detail").asText("Invalid request to Amadeus API.");
                    throw new IllegalArgumentException(message);
                }

                JsonNode data = root.path("data");
                if (!data.isArray() || data.isEmpty()) {
                    throw new IllegalArgumentException("No flights were found between the selected cities on that date.");
                }

                for (JsonNode offer : data) {
                    JsonNode itinerary = offer.path("itineraries").get(0);
                    JsonNode segment = itinerary.path("segments").get(0);
                    JsonNode priceNode = offer.path("price");

                    String airline = segment.path("carrierCode").asText();
                    String depTime = segment.path("departure").path("at").asText();
                    String arrTime = segment.path("arrival").path("at").asText();
                    double price = priceNode.path("total").asDouble();

                    results.add(new FlightResult(
                            airline,
                            origin,
                            destination,
                            depTime,
                            arrTime,
                            price
                    ));
                }
            } catch (IllegalArgumentException e) {
                throw e; // Re-throw so controller can handle it
            } catch (Exception e) {
                throw new RuntimeException("Failed to parse Amadeus response.");
            }
        }

        return results;
    }

    /**
     * Returns static fallback flight data in case the Amadeus API is not used or fails.
     *
     * @param request the search request input
     * @return single-item list of a sample {@link FlightResult}
     */
    public List<FlightResult> searchFlights(FlightSearchRequest request) {
        return List.of(new FlightResult(
                "Fallback Airlines",
                request.getOrigin(),
                request.getDestination(),
                "10:00",
                "13:00",
                299.99
        ));
    }

    /**
     * Authenticates with the Amadeus API and retrieves an OAuth2 bearer token.
     *
     * @return valid access token as a string
     * @throws IllegalStateException if the token is not present in the API response
     */
    public String getAccessToken() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        String requestBody = "grant_type=client_credentials"
                + "&client_id=" + amadeusProperties.getClientId()
                + "&client_secret=" + amadeusProperties.getClientSecret();

        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);

        ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                amadeusProperties.getTokenUrl(),
                HttpMethod.POST,
                request,
                new ParameterizedTypeReference<>() {}
        );

        Map<String, Object> responseBody = response.getBody();
        if (responseBody == null || !responseBody.containsKey("access_token")) {
            throw new IllegalStateException("Failed to retrieve access token from Amadeus response.");
        }

        return responseBody.get("access_token").toString();
    }
}
