package com.mock_project.mock_project.repository;

import com.mock_project.mock_project.model.VariantProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VariantProductRepository extends JpaRepository<VariantProduct, Long> {
}
