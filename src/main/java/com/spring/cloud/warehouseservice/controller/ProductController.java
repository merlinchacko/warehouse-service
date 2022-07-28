package com.spring.cloud.warehouseservice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.cloud.warehouseservice.domain.entity.Product;
import com.spring.cloud.warehouseservice.domain.model.ProductRequest;
import com.spring.cloud.warehouseservice.service.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;


@RestController
@RequestMapping("/warehouse/product")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
@Api(produces = "application/json", value = "Products", tags = {"Products"})
public class ProductController
{
    private ProductService productService;

    @PostMapping
    @ApiOperation(value = "Create a new product", response = Product.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully created a new product", response = Product.class),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
        @ApiResponse(code = 500, message = "Application failed to process the request")
    }
    )
    public Product createProduct(@RequestBody ProductRequest productRequest)
    {
        return productService.save(productRequest);
    }

    @GetMapping
    @ApiOperation(value = "Retrieve all products", response = Product.class, responseContainer = "List")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved all the products", response = Product.class, responseContainer = "List"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
        @ApiResponse(code = 500, message = "Application failed to process the request")
    }
    )
    public List<Product> getProducts()
    {
        return productService.getAll();
    }
}
