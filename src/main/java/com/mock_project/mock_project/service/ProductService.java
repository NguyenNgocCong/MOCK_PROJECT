package com.mock_project.mock_project.service;

import com.mock_project.mock_project.dto.ProductDTO;
import com.mock_project.mock_project.dto.ProductDetailDTO;
import com.mock_project.mock_project.model.Product;

public interface ProductService {
    Product createProduct(ProductDTO productDTO);
    Product updateProduct(Long productId, ProductDTO updatedProductDTO);
    void deleteProduct(Long productId);
    ProductDetailDTO getProductDetailById(Long productId);
}
