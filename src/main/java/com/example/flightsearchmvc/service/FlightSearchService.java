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
 * Main service layer responsible for processing flight searches.
 * Communicates with the Amadeus API or uses fallback logic.
 */
@Service
public class FlightSearchService {

    private final RestTemplate restTemplate;
    private final AmadeusProperties amadeusProperties;

    /**
     * Constructor for dependency injection
     *
     * @param restTemplate       Spring RestTemplate for making HTTP requests
     * @param amadeusProperties  Configuration holding API keys and URLs
     */
    public FlightSearchService(RestTemplate restTemplate, AmadeusProperties amadeusProperties) {
        this.restTemplate = restTemplate;
        this.amadeusProperties = amadeusProperties;
    }

    /**
     * Uses Amadeus API to find flight offers based on user request.
     *
     * @param request Flight search request containing origin, destination, and date
     * @return A list of flight results, parsed from Amadeus API response
     * @throws RuntimeException if response parsing fails
     */
    public List<FlightResult> searchWithAmadeus(FlightSearchRequest request) {
        String token = getAccessToken();  // Get a valid bearer token for authorization

        // Construct request URL with path parameters
        String apiUrl = "https://test.api.amadeus.com/v2/shopping/flight-offers"
                + "?originLocationCode={origin}"
                + "&destinationLocationCode={destination}"
                + "&departureDate={date}"
                + "&adults=1&max=5";

        // Set authorization and content type headers
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // Fill URL placeholders with user-supplied values
        Map<String, String> uriParams = new HashMap<>();
        uriParams.put("origin", request.getOrigin().toUpperCase());
        uriParams.put("destination", request.getDestination().toUpperCase());
        uriParams.put("date", request.getDate().toString());

        // Make the GET call to Amadeus and receive the raw JSON response
        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, String.class, uriParams);

        List<FlightResult> results = new ArrayList<>();
        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            try {
                // Parse the JSON response to extract flight data
                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(response.getBody());
                JsonNode data = root.path("data");

                for (JsonNode offer : data) {
                    JsonNode itinerary = offer.path("itineraries").get(0);
                    JsonNode segment = itinerary.path("segments").get(0);
                    JsonNode priceNode = offer.path("price");

                    String airline = segment.path("carrierCode").asText();
                    String depTime = segment.path("departure").path("at").asText();
                    String arrTime = segment.path("arrival").path("at").asText();
                    double price = priceNode.path("total").asDouble();

                    // Create a result object for each offer and add to list
                    results.add(new FlightResult(
                        airline,
                        request.getOrigin(),
                        request.getDestination(),
                        depTime,
                        arrTime,
                        price
                    ));
                }
            } catch (Exception e) {
                throw new RuntimeException("Failed to parse Amadeus response.");
            }
        }
        return results;
    }

    /**
     * Returns a hardcoded fallback result if the Amadeus API is not used.
     *
     * @param request Flight search request
     * @return List with a single fallback flight result
     */
    public List<FlightResult> searchFlights(FlightSearchRequest request) {
        return List.of(new FlightResult(
        "Fallback Airlines",
        request.getOrigin(),
        request.getDestination(),
        "10:00",
        "13:00",
        299.99));
    }

    /**
     * Retrieves a bearer token from the Amadeus OAuth2 endpoint.
     *
     * @return A valid OAuth2 access token as a String
     */
    public String getAccessToken() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // Prepare URL-encoded request body with client credentials
        String requestBody = "grant_type=client_credentials"
                + "&client_id=" + amadeusProperties.getClientId()
                + "&client_secret=" + amadeusProperties.getClientSecret();

        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);

        // POST request to obtain access token
        ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
            amadeusProperties.getTokenUrl(),
            HttpMethod.POST,
            request,
            new ParameterizedTypeReference<>() {}
        );

        // Extract and return token from response
        Map<String, Object> responseBody = response.getBody();
        if (responseBody == null || !responseBody.containsKey("access_token")) {
            throw new IllegalStateException("Failed to retrieve access token from Amadeus response.");
        }

        return responseBody.get("access_token").toString();
    }
}
