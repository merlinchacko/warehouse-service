package com.spring.cloud.warehouseservice.exception;

public class IdNotFoundException extends RuntimeException
{

    public IdNotFoundException(Long id)
    {
        super("Id - " + id + "not found");
    }
}
