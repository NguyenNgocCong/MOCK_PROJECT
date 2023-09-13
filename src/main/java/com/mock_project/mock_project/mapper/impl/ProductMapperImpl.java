package com.mock_project.mock_project.mapper.impl;

import com.mock_project.mock_project.dto.ProductDTO;
import com.mock_project.mock_project.mapper.ProductMapper;
import com.mock_project.mock_project.model.Category;
import com.mock_project.mock_project.model.Product;
import org.springframework.stereotype.Component;


public class ProductMapperImpl implements ProductMapper {
    @Override
    public ProductDTO toProductDTO(Product product) {
        if (product == null) {
            return null;
        }

        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setCategoryId(product.getCategory().getId());

        return productDTO;
    }

    @Override
    public Product productDTOToProduct(ProductDTO productDTO) {
        if (productDTO == null) {
            return null;
        }

        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());

        // Ánh xạ categoryId thành đối tượng Category (giả sử bạn có lớp Category)
        Category category = new Category();
        category.setId(productDTO.getCategoryId());
        product.setCategory(category);

        // Các phần còn lại của ánh xạ dữ liệu từ ProductDTO vào Product
        // Ví dụ: product.setDescription(productDTO.getDescription());
        // product.setPrice(productDTO.getPrice());

        return product;
    }
}
