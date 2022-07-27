package com.spring.cloud.warehouseservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.cloud.warehouseservice.domain.entity.Box;

@Repository
public interface BoxRepository extends JpaRepository<Box, Long>
{
    @Query("Select b from Box b LEFT JOIN Product p ON b.product.id = p.id where b.product.id IN (SELECT pr.id FROM Product pr WHERE " +
        "pr.name LIKE CONCAT('%',:query, '%')) order by p.name")
    List<Box> findBySearchQuery(String query);
}
