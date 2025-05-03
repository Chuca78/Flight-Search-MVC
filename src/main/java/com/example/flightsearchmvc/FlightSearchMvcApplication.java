package com.example.flightsearchmvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Main application class for launching the Flight Search MVC project.
 * 
 * This initializes the Spring Boot application context and starts the web server.
 */
@SpringBootApplication
public class FlightSearchMvcApplication {

    /**
     * Entry point for the Spring Boot application.
     * 
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(FlightSearchMvcApplication.class, args);
    }

    /**
    * Configures a RestTemplate bean for making HTTP requests.
    * Used by the FlightController to call the REST API internally.
    *
    * @return a new RestTemplate instance
    */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();

    }
}

