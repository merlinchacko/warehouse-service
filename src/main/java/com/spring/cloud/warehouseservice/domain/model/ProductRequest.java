package com.spring.cloud.warehouseservice.domain.model;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProductRequest
{
    private String name;
    private ProductType type;
}
