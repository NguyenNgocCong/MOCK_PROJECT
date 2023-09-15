package com.mock_project.mock_project.service;

import com.mock_project.mock_project.model.Category;

import java.util.List;

public interface CategoryService {
    Category getCategoryById(Long categoryId);
    boolean exists(Long categoryId);
    List<Category> getAllCategories();

}
