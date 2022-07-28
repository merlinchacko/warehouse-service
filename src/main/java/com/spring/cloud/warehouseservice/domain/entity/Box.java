package com.spring.cloud.warehouseservice.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "box")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Box
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(nullable = false)
    @NotEmpty(message = "Box description should not be empty")
    @Size(min = 2, message = "Box description should have at least 2 characters")
    private String description;

    @OneToOne
    @JoinColumn(name = "product_id", nullable = false, referencedColumnName = "id", unique = true)
    private Product product;

    @OneToOne
    @JoinColumn(name = "location_id", nullable = false, referencedColumnName = "id")
    private Location location;

    public Box(final String description, final Product product, final Location location)
    {
        this.description = description;
        this.product = product;
        this.location = location;
    }
}
