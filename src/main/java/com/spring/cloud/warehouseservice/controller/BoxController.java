package com.spring.cloud.warehouseservice.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.cloud.warehouseservice.domain.entity.Box;
import com.spring.cloud.warehouseservice.domain.model.BoxRequest;
import com.spring.cloud.warehouseservice.service.BoxService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;

@Validated
@RestController
@RequestMapping("/warehouse/box")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
@Api(produces = "application/json", value = "Warehouse boxes", tags = {"Boxes"})
public class BoxController
{
    private BoxService boxService;

    @PostMapping
    @ApiOperation(value = "Create a new warehouse box", response = Box.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully created a new box"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
        @ApiResponse(code = 500, message = "Application failed to process the request")
    }
    )
    public Box createBox(@Valid @RequestBody BoxRequest boxRequest)
    {
        return boxService.save(boxRequest);
    }

    @GetMapping
    @ApiOperation(value = "Retrieve all boxes", response = Box.class, responseContainer = "List")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved all the boxes", response = Box.class, responseContainer = "List"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
        @ApiResponse(code = 500, message = "Application failed to process the request")
    }
    )
    public List<Box> getBoxes()
    {
        return boxService.getAll();
    }

    @GetMapping("/search")
    @ApiOperation(value = "Retrieve specific box based on search query parameter", response = Box.class, responseContainer = "List")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved the box", response = Box.class, responseContainer = "List"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
        @ApiResponse(code = 500, message = "Application failed to process the request")
    }
    )
    public List<Box> getBoxBySearchQuery(@RequestParam("query") @NotNull String query){
        return boxService.getBoxBySearchQuery(query);
    }

}
