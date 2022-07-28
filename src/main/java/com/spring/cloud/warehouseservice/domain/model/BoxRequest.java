package com.spring.cloud.warehouseservice.domain.model;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class BoxRequest
{
	private String description;
	private Long productId;
	private Long locationId;

    public BoxRequest(final String description, final Long productId, final Long locationId)
    {
        this.description = description;
        this.productId = productId;
        this.locationId = locationId;
    }
}
