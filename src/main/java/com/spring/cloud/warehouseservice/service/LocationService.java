package com.spring.cloud.warehouseservice.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.cloud.warehouseservice.domain.entity.Location;
import com.spring.cloud.warehouseservice.repository.LocationRepository;

import lombok.AllArgsConstructor;


@Service
@Transactional
@AllArgsConstructor
public class LocationService
{
    private LocationRepository locationRepository;

    public List<Location> getAll()
    {
        return locationRepository.findAll();
    }
}
