package org.example.locationservice.services;

import org.example.locationservice.dto.DriverLocationDto;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.GeoOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// Marks this class as a Spring service component
@Service
public class RedisLocationServiceImpl implements LocationService {

    // Redis key under which driver location data is stored using geospatial indexes
    private static final String DRIVER_GEO_OPS_KEY = "drivers";
    // Radius (in kilometers) to search for nearby drivers
    private static final Double SEARCH_RADIUS = 100.0;

    // Template for Redis operations on string keys/values, including geo commands
    private final StringRedisTemplate stringRedisTemplate;

    // Constructor injection for the Redis template
    public RedisLocationServiceImpl(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    /**
     * Save a driver's location in Redis using Geo commands.
     *
     * @param driverId  Unique identifier of the driver
     * @param latitude  Latitude coordinate to save
     * @param longitude Longitude coordinate to save
     * @return          Always returns true (could be extended for error handling)
     */
    @Override
    public Boolean saveDriverLocation(String driverId, Double latitude, Double longitude) {
        // Obtain GeoOperations to perform geospatial commands
        GeoOperations<String, String> geoOps = stringRedisTemplate.opsForGeo();
        // Add the driver's location point to the Redis geospatial index
        geoOps.add(
                DRIVER_GEO_OPS_KEY,
                new RedisGeoCommands.GeoLocation<>(
                        driverId,
                        new Point(
                                latitude,
                                longitude)
                )
        );
        // Indicate successful save (error handling can be added later)
        return true;
    }

    /**
     * Retrieve drivers within a predefined radius of given coordinates.
     *
     * @param latitude  Latitude center for the search
     * @param longitude Longitude center for the search
     * @return          List of DriverLocationDto for each nearby driver
     */
    @Override
    public List<DriverLocationDto> getNearByDrivers(Double latitude, Double longitude) {
        // Obtain GeoOperations to perform geospatial commands
        GeoOperations<String, String> geoOps = stringRedisTemplate.opsForGeo();
        // Define search radius in kilometers
        Distance radius = new Distance(SEARCH_RADIUS, Metrics.KILOMETERS);
        // Create a circular area centered at the given point with the search radius
        Circle within = new Circle(new Point(latitude, longitude), radius);

        // Execute Redis GEO radius query to find matching locations
        GeoResults<RedisGeoCommands.GeoLocation<String>> results =
                geoOps.radius(DRIVER_GEO_OPS_KEY, within);

        // Prepare list to map Redis results into DTOs
        List<DriverLocationDto> drivers = new ArrayList<>();
        // Iterate over each result returned by Redis
        for (GeoResult<RedisGeoCommands.GeoLocation<String>> result : results) {
            // Fetch the precise location coordinates for this driver
            Point point = geoOps.position(DRIVER_GEO_OPS_KEY, result.getContent().getName()).get(0);
            // Build a DTO representing the driver's ID and coordinates
            DriverLocationDto driverLocation = DriverLocationDto.builder()
                    .driverId(result.getContent().getName())
                    .latitude(point.getX())
                    .longitude(point.getY())
                    .build();
            // Add the DTO to the response list
            drivers.add(driverLocation);
        }
        // Return the list of nearby driver DTOs
        return drivers;
    }
}
