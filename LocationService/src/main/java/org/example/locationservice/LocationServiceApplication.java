package org.example.locationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Indicates a Spring Boot application and triggers auto-configuration and component scanning
@SpringBootApplication
public class LocationServiceApplication {

    // Entry point of the Spring Boot application
    public static void main(String[] args) {
        // Bootstraps the application, starting the Spring context
        SpringApplication.run(LocationServiceApplication.class, args);
    }

}
