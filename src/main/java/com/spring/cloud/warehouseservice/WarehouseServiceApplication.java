package com.spring.cloud.warehouseservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;


@EnableEncryptableProperties
@SpringBootApplication
public class WarehouseServiceApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(WarehouseServiceApplication.class, args);
    }

}
