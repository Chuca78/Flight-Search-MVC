package com.example.flightsearchmvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.example.flightsearchmvc.config.AmadeusProperties;

/**
 * Entry point for the Flight Search MVC Spring Boot application.
 * 
 * This class initializes the Spring Boot application context,
 * registers beans, and starts the embedded server.
 */
@SpringBootApplication
@EnableConfigurationProperties(AmadeusProperties.class)
public class FlightSearchMvcApplication {

    /**
     * Main method to launch the Spring Boot application.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        SpringApplication.run(FlightSearchMvcApplication.class, args);
    }

    /**
     * Creates a RestTemplate bean used for performing HTTP calls
     * (e.g., to the Amadeus flight offers API).
     *
     * @return a configured RestTemplate instance
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
