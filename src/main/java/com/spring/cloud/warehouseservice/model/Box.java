package com.spring.cloud.warehouseservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;


@Entity
@Table(name = "product")
@Data
public class Box
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @OneToOne
    @JoinColumn(name = "product_id", nullable = false, referencedColumnName = "id")
    @JsonProperty
    private Product product;

    @OneToOne
    @JoinColumn(name = "location_id", nullable = false, referencedColumnName = "id")
    @JsonProperty
    private Location location;
}
