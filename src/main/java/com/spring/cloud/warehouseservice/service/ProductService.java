package com.spring.cloud.warehouseservice.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.cloud.warehouseservice.domain.entity.Product;
import com.spring.cloud.warehouseservice.domain.model.ProductRequest;
import com.spring.cloud.warehouseservice.repository.ProductRepository;

import lombok.AllArgsConstructor;


@Service
@Transactional
@AllArgsConstructor
public class ProductService
{
    private ProductRepository productRepository;

    public Product save(final ProductRequest productRequest)
    {
        Product product = new Product(productRequest.getName(), productRequest.getType());

        return productRepository.save(product);
    }
}
