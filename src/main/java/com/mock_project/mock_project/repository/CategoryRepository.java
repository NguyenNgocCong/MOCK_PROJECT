package com.mock_project.mock_project.repository;

import com.mock_project.mock_project.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Optional<Category> findById(Long categoryId);
}
