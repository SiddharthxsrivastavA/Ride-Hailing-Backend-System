package org.example.locationservice.dto;

import lombok.*;

// Lombok annotation to generate getters for all fields
@Getter
// Lombok annotation to generate setters for all fields
@Setter
// Lombok annotation to implement the Builder pattern for this class
@Builder
// Lombok annotation to generate a no-argument constructor
@NoArgsConstructor
// Lombok annotation to generate an all-arguments constructor
@AllArgsConstructor
public class DriverLocationDto {
    // Unique identifier for the driver
    private String driverId;

    // Current latitude of the driver
    private Double latitude;

    // Current longitude of the driver
    private Double longitude;
}
