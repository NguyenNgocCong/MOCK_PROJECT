package com.mock_project.mock_project.repository;

import com.mock_project.mock_project.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
