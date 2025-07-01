package org.example.locationservice.controllers;

import org.example.locationservice.services.LocationService;
import org.example.locationservice.dto.DriverLocationDto;
import org.example.locationservice.dto.NearbyDriversRequestDto;
import org.example.locationservice.dto.SaveDriverLocationRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

// Marks this class as a Spring REST controller handling HTTP requests
@RestController
// Base URL path for all endpoints in this controller
@RequestMapping("/api/location")
public class LocationController {

    // Service layer dependency for location-related operations
    private LocationService locationService;

    // Constructor injection for the LocationService bean
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    // Endpoint to save a driver's current location
    @PostMapping("/drivers")
    public ResponseEntity<Boolean> saveDriverLocation(
            // Maps incoming JSON payload to DTO
            @RequestBody SaveDriverLocationRequestDto saveDriverLocationRequestDto) {
        try {
            // Call service to persist driver location data
            Boolean response = locationService.saveDriverLocation(
                    saveDriverLocationRequestDto.getDriverId(),
                    saveDriverLocationRequestDto.getLatitude(),
                    saveDriverLocationRequestDto.getLongitude()
            );
            // Return CREATED status with service response (true/false)
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            // On error, return false and 500 status
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint to retrieve nearby drivers based on given coordinates
    @GetMapping("/nearby/drivers")
    public ResponseEntity<List<DriverLocationDto>> getNearbyDrivers(
            // Maps incoming JSON payload to DTO for query parameters
            @RequestBody NearbyDriversRequestDto nearbyDriversRequestDto) {
        try {
            // Call service to fetch list of nearby drivers
            List<DriverLocationDto> drivers = locationService.getNearByDrivers(
                    nearbyDriversRequestDto.getLatitude(),
                    nearbyDriversRequestDto.getLongitude()
            );
            // Return OK status with list of driver locations
            return new ResponseEntity<>(drivers, HttpStatus.OK);
        } catch (Exception e) {
            // Log error message to console for debugging
            System.out.println(e.getMessage());
            // On error, return empty list and 500 status
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
