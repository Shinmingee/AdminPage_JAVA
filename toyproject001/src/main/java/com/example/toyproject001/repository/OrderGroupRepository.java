package com.example.toyproject001.repository;

import com.example.toyproject001.model.entity.OrderGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderGroupRepository extends JpaRepository<OrderGroup, Long> {
}
