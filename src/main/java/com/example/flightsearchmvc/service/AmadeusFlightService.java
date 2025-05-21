// package com.example.flightsearchmvc.service;

// import com.example.flightsearchmvc.config.AmadeusProperties;
// import org.springframework.core.ParameterizedTypeReference;
// import org.springframework.http.*;
// import org.springframework.stereotype.Service;
// import org.springframework.web.client.RestTemplate;

// import java.util.*;

// /**
//  * Service responsible for communicating with the Amadeus API.
//  * Handles authentication and search request logic.
//  */
// @Service
// public class AmadeusFlightService {

//     private final RestTemplate restTemplate;
//     private final AmadeusProperties amadeusProperties;

//     /**
//      * Constructor for injecting dependencies.
//      *
//      * @param restTemplate       Spring's HTTP client
//      * @param amadeusProperties  Configuration properties for the Amadeus API
//      */
//     public AmadeusFlightService(RestTemplate restTemplate, AmadeusProperties amadeusProperties) {
//         this.restTemplate = restTemplate;
//         this.amadeusProperties = amadeusProperties;
//     }

//     /**
//      * Retrieves an OAuth2 access token from the Amadeus API.
//      *
//      * @return A valid access token string
//      */
//     public String getAccessToken() {
//         HttpHeaders headers = new HttpHeaders();
//         headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

//         // Request body with credentials and grant type
//         String body = "grant_type=client_credentials"
//                 + "&client_id=" + amadeusProperties.getClientId()
//                 + "&client_secret=" + amadeusProperties.getClientSecret();

//         HttpEntity<String> request = new HttpEntity<>(body, headers);

//         // Send POST request to token endpoint
//         ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
//                 amadeusProperties.getTokenUrl(),
//                 HttpMethod.POST,
//                 request,
//                 new ParameterizedTypeReference<>() {}
//         );

//         // Parse and return the access token
//         Map<String, Object> responseBody = response.getBody();
//         if (responseBody == null || !responseBody.containsKey("access_token")) {
//             throw new IllegalStateException("Failed to retrieve access token from Amadeus.");
//         }

//         return responseBody.get("access_token").toString();
//     }

//     /**
//      * Searches for flight offers using the Amadeus API.
//      *
//      * @param origin        IATA code of departure airport
//      * @param destination   IATA code of destination airport
//      * @param departureDate Date of departure (YYYY-MM-DD)
//      * @return Raw JSON string containing flight offers
//      */
//     public String searchFlights(String origin, String destination, String departureDate) {
//         String token = getAccessToken();

//         HttpHeaders headers = new HttpHeaders();
//         headers.setBearerAuth(token);
//         headers.setAccept(List.of(MediaType.APPLICATION_JSON));

//         // Build the query URL using provided parameters
//         String url = amadeusProperties.getApiUrl()
//                 + "?originLocationCode=" + origin
//                 + "&destinationLocationCode=" + destination
//                 + "&departureDate=" + departureDate
//                 + "&adults=1";

//         HttpEntity<Void> request = new HttpEntity<>(headers);

//         // Send GET request to the Amadeus flight offers endpoint
//         ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);

//         // Return JSON body if successful; otherwise, throw an error
//         if (response.getStatusCode() == HttpStatus.OK) {
//             return response.getBody();
//         } else {
//             throw new RuntimeException("Failed to fetch flight offers");
//         }
//     }
// }
