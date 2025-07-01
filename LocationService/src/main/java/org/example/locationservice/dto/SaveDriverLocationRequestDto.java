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
public class SaveDriverLocationRequestDto {
    // Identifier for the driver whose location is being saved
    private String driverId;

    // Latitude coordinate of the driver's location
    private Double latitude;

    // Longitude coordinate of the driver's location
    private Double longitude;
}
