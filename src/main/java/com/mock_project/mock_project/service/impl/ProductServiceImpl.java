package com.mock_project.mock_project.service.impl;

import com.mock_project.mock_project.dto.ProductDTO;
import com.mock_project.mock_project.dto.ProductDetailDTO;
import com.mock_project.mock_project.mapper.CategoryMapper;
import com.mock_project.mock_project.mapper.ProductMapper;
import com.mock_project.mock_project.mapper.VariantProductMapper;
import com.mock_project.mock_project.model.Product;
import com.mock_project.mock_project.repository.ProductRepository;
import com.mock_project.mock_project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductMapper productMappers;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private VariantProductMapper variantProductMapper;

    @Override
    public Product createProduct(ProductDTO productDTO) {
        return null;
    }

    @Override
    public Product updateProduct(Long productId, ProductDTO updatedProductDTO) {
        return null;
    }

    @Override
    public void deleteProduct(Long productId) {

    }

    @Override
    public ProductDetailDTO getProductDetailById(Long productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            ProductDetailDTO productDetailDTO = new ProductDetailDTO();
            productDetailDTO.setProduct(productMappers.toProductDTO(product));
            productDetailDTO.setCategory(categoryMapper.toCategoryDTO(product.getCategory()));
            productDetailDTO.setVariantProducts(variantProductMapper.toVariantProductDTOList(product.getVariantProducts()));
            return productDetailDTO;
        }
        return null;
    }
}
