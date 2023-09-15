package com.mock_project.mock_project.service;

import com.mock_project.mock_project.dto.ProductDTO;
import com.mock_project.mock_project.dto.ProductDetailDTO;
import com.mock_project.mock_project.dto.VariantProductDTO;
import com.mock_project.mock_project.model.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(ProductDTO productDTO);
    Product updateProduct(Long productId, ProductDTO updatedProductDTO);
    void deleteProduct(Long productId);
    ProductDetailDTO getProductDetailById(Long productId);
    List<VariantProductDTO> getVariantProductsById(Long id);
    List<Product> getProductsByCategory(Long categoryId, int pageNo, int pageSize);

}
