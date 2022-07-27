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
}
