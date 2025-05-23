package com.example.flightsearchmvc.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Configuration class that maps properties defined in application.properties using the "amadeus" prefix.
 * These values are used to configure access to the Amadeus API.
 */
@Component
@ConfigurationProperties(prefix = "amadeus")
public class AmadeusProperties {

    private String clientId;
    private String clientSecret;
    private String tokenUrl;
    private String apiUrl;

    /**
     * Gets the Amadeus API client ID.
     * @return client ID
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * Sets the Amadeus API client ID.
     * @param clientId the client ID
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    /**
     * Gets the Amadeus API client secret.
     * @return client secret
     */
    public String getClientSecret() {
        return clientSecret;
    }

    /**
     * Sets the Amadeus API client secret.
     * @param clientSecret the client secret
     */
    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    /**
     * Gets the token URL used to retrieve access tokens from Amadeus.
     * @return token URL
     */
    public String getTokenUrl() {
        return tokenUrl;
    }

    /**
     * Sets the token URL for Amadeus OAuth authentication.
     * @param tokenUrl the token endpoint
     */
    public void setTokenUrl(String tokenUrl) {
        this.tokenUrl = tokenUrl;
    }

    /**
     * Gets the base API URL for searching flight offers.
     * @return base API URL
     */
    public String getApiUrl() {
        return apiUrl;
    }

    /**
     * Sets the base API URL for Amadeus flight offers.
     * @param apiUrl the API URL
     */
    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }
}
