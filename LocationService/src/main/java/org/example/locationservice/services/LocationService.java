package org.example.locationservice.services;

import org.example.locationservice.dto.DriverLocationDto;

import java.util.List;

// Service interface defining contract for location-related operations
public interface LocationService {

    /**
     * Persist a driver's current location coordinates.
     *
     * @param driverId  Unique identifier of the driver
     * @param latitude  Latitude coordinate of the driver
     * @param longitude Longitude coordinate of the driver
     * @return          Boolean indicating success (true) or failure (false)
     */
    Boolean saveDriverLocation(String driverId, Double latitude, Double longitude);

    /**
     * Retrieve a list of drivers near the specified coordinates.
     *
     * @param latitude  Latitude coordinate to search around
     * @param longitude Longitude coordinate to search around
     * @return          List of DriverLocationDto objects representing nearby drivers
     */
    List<DriverLocationDto> getNearByDrivers(Double latitude, Double longitude);

}
