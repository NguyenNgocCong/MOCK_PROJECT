package com.mock_project.mock_project.mapper;

import com.mock_project.mock_project.dto.ProductDTO;
import com.mock_project.mock_project.model.Category;
import com.mock_project.mock_project.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "category", target = "categoryId", qualifiedByName = "categoryToLong")
    ProductDTO toProductDTO(Product product);
    @Mapping(source = "categoryId", target = "category.id")
    Product productDTOToProduct(ProductDTO productDTO);
    // Ánh xạ từ Category sang Long
    @Named("categoryToLong")
    default Long mapCategoryToLong(Category category) {
        if (category != null) {
            return category.getId();
        }
        return null;
    }
    // Ánh xạ từ Long sang Category
    @Named("longToCategory")
    default Category mapLongToCategory(Long categoryId) {
        if (categoryId != null) {
            Category category = new Category();
            category.setId(categoryId);
            return category;
        }
        return null;
    }
}
