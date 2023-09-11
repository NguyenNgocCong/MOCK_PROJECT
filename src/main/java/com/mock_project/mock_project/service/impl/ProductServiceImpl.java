package com.mock_project.mock_project.service.impl;

import com.mock_project.mock_project.dto.ProductDTO;
import com.mock_project.mock_project.dto.ProductDetailDTO;
import com.mock_project.mock_project.mapper.CategoryMapper;
import com.mock_project.mock_project.mapper.ProductMapper;
import com.mock_project.mock_project.mapper.VariantProductMapper;
import com.mock_project.mock_project.model.Product;
import com.mock_project.mock_project.repository.ProductRepository;
import com.mock_project.mock_project.service.CategoryService;
import com.mock_project.mock_project.service.ProductService;
import com.mock_project.mock_project.service.VariantProductService;
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

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private VariantProductService variantProductService;

    @Override
    public Product createProduct(ProductDTO productDTO) {

        if (productDTO.getCategoryId() == null || !categoryService.exists(productDTO.getCategoryId())) {
            return null;
        }

        Product product = productMappers.toProduct(productDTO);

        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long productId, ProductDTO updatedProductDTO) {
//        // Kiểm tra xem sản phẩm có tồn tại không
//        if (!productRepository.existsById(productId)) {
//            return null; // Hoặc bạn có thể ném ra một exception tùy theo yêu cầu
//        }
//
//        // Cập nhật thông tin sản phẩm từ updatedProductDTO
//        Product existingProduct = productRepository.getOne(productId);
//        existingProduct.setName(updatedProductDTO.getName());
//        existingProduct.setDescription(updatedProductDTO.getDescription());
//
//        // Lưu sản phẩm cập nhật vào cơ sở dữ liệu
//        return productRepository.save(existingProduct);
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
