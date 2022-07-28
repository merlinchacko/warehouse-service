package com.spring.cloud.warehouseservice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.cloud.warehouseservice.domain.entity.Location;
import com.spring.cloud.warehouseservice.service.LocationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;


@RestController
@RequestMapping("/warehouse/location")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
@Api(produces = "application/json", value = "Locations", tags = {"Locations"})
public class LocationController
{
    private LocationService locationService;

    @GetMapping
    @ApiOperation(value = "Retrieve all locations", response = Location.class, responseContainer = "List")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved all the locations", response = Location.class, responseContainer = "List"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
        @ApiResponse(code = 500, message = "Application failed to process the request")
    }
    )
    public List<Location> getLocations()
    {
        return locationService.getAll();
    }
}
