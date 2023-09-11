package com.mock_project.mock_project.mapper.impl;

import com.mock_project.mock_project.dto.ProductDTO;
import com.mock_project.mock_project.mapper.ProductMapper;
import com.mock_project.mock_project.model.Category;
import com.mock_project.mock_project.model.Product;
import com.mock_project.mock_project.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductMapperImpl implements ProductMapper {
    @Autowired
    private CategoryService categoryService;
    @Override
    public ProductDTO toProductDTO(Product product) {
        if (product == null) {
            return null;
        }

        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setCategoryId(product.getCategory() != null ? product.getCategory().getId() : null);
        // Các thông tin khác của sản phẩm DTO

        return productDTO;
    }

    @Override
    public Product toProduct(ProductDTO productDTO) {
        if (productDTO == null) {
            return null;
        }
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        // Đây là nơi bạn cần lấy thông tin Category từ categoryId
        // và thiết lập nó trong product
        if (productDTO.getCategoryId() != null) {
            Category category = categoryService.getCategoryById(productDTO.getCategoryId());
            product.setCategory(category);
        }
        return product;
    }
}
