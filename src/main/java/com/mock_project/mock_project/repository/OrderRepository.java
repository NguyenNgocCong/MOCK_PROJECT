package com.mock_project.mock_project.repository;

import com.mock_project.mock_project.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}