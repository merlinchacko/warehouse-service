package com.spring.cloud.warehouseservice.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.spring.cloud.warehouseservice.domain.model.ProductType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(nullable = false)
    @NotEmpty(message = "Product name should not be empty")
    @Size(min = 2, message = "Product name should have at least 2 characters")
    private String name;

    @Enumerated(EnumType.STRING)
    private ProductType type;

    public Product(final String name, final ProductType type)
    {
        this.name = name;
        this.type = type;
    }
}
