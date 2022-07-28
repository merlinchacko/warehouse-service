package com.spring.cloud.warehouseservice.exception;

public class IdNotFoundException extends RuntimeException
{

    public IdNotFoundException(String item, Long id)
    {
        super(item + "Id - " + id + " not found");
    }
}
