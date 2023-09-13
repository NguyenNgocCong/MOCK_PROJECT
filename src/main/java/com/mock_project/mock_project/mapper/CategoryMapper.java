package com.mock_project.mock_project.mapper;

import com.mock_project.mock_project.dto.CategoryDTO;
import com.mock_project.mock_project.model.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDTO toCategoryDTO(Category category);

    Category toCategory(CategoryDTO categoryDTO);
}
