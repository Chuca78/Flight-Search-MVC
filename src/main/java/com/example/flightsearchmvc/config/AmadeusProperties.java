package com.example.flightsearchmvc.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Configuration class that maps properties defined in application.yml or application.properties
 * using the prefix "amadeus". These properties are used to configure access to the Amadeus API.
 */
@Component 
@ConfigurationProperties(prefix = "amadeus")
public class AmadeusProperties {

    // Client ID used for Amadeus API authentication
    private String clientId;

    // Client secret used for Amadeus API authentication
    private String clientSecret;

    // URL to retrieve OAuth access tokens
    private String tokenUrl;

    // Base URL to access Amadeus flight offer APIs
    private String apiUrl;

    // Getter and setter for clientId
    public String getClientId() {
        return clientId;
    }
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    // Getter and setter for clientSecret
    public String getClientSecret() {
        return clientSecret;
    }
    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    // Getter and setter for tokenUrl
    public String getTokenUrl() {
        return tokenUrl;
    }
    public void setTokenUrl(String tokenUrl) {
        this.tokenUrl = tokenUrl;
    }

    // Getter and setter for apiUrl
    public String getApiUrl() {
        return apiUrl;
    }
    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }
}
