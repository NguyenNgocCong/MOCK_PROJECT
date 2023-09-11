package com.mock_project.mock_project.service.impl;

import com.mock_project.mock_project.model.Category;
import com.mock_project.mock_project.repository.CategoryRepository;
import com.mock_project.mock_project.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public Category getCategoryById(Long categoryId) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        return categoryOptional.orElse(null);
    }

    @Override
    public boolean exists(Long categoryId) {
        return categoryRepository.existsById(categoryId);
    }
}
