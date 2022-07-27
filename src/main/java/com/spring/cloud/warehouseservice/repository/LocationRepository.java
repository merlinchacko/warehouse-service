package com.spring.cloud.warehouseservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.cloud.warehouseservice.domain.entity.Location;


@Repository
public interface LocationRepository extends JpaRepository<Location, Long>
{
}
