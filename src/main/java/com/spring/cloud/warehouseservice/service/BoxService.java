package com.spring.cloud.warehouseservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.cloud.warehouseservice.domain.entity.Box;
import com.spring.cloud.warehouseservice.domain.entity.Location;
import com.spring.cloud.warehouseservice.domain.entity.Product;
import com.spring.cloud.warehouseservice.domain.model.BoxRequest;
import com.spring.cloud.warehouseservice.exception.IdNotFoundException;
import com.spring.cloud.warehouseservice.repository.BoxRepository;
import com.spring.cloud.warehouseservice.repository.LocationRepository;
import com.spring.cloud.warehouseservice.repository.ProductRepository;

import lombok.AllArgsConstructor;


@Service
@Transactional
@AllArgsConstructor
public class BoxService
{
    private BoxRepository boxRepository;
    private LocationRepository locationRepository;
    private ProductRepository productRepository;

    public Box save(final BoxRequest boxRequest)
    {
        Optional<Location> locationOpt = locationRepository.findById(boxRequest.getLocationId());
        Location location = locationOpt.orElseThrow(() -> new IdNotFoundException(boxRequest.getLocationId()));

        Optional<Product> productOpt = productRepository.findById(boxRequest.getProductId());
        Product product = productOpt.orElseThrow(() -> new IdNotFoundException(boxRequest.getProductId()));

        Box box = new Box(boxRequest.getDescription(), product, location);

        return boxRepository.save(box);
    }

    public List<Box> getAll()
    {
        return boxRepository.findAll();
    }

    public List<Box> getBoxBySearchQuery(final String query)
    {
        return boxRepository.findBySearchQuery(query);
    }
}
