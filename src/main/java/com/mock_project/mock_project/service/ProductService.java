package com.mock_project.mock_project.service;

import com.mock_project.mock_project.dto.ProductDTO;
import com.mock_project.mock_project.dto.ProductDetailDTO;
import com.mock_project.mock_project.dto.VariantProductDTO;
import com.mock_project.mock_project.model.Product;
import com.mock_project.mock_project.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.List;

public interface ProductService {
    Product createProduct(ProductDTO productDTO);
    Product updateProduct(Long productId, ProductDTO updatedProductDTO);
    void deleteProduct(Long productId);
    ProductDetailDTO getProductDetailById(Long productId);
    List<VariantProductDTO> getVariantProductsById(Long id);
    @Autowired
    ProductRepository productRepository = null;
    public default Product getProductById(Long productId) throws ProductNotFoundException {
        // Sử dụng productRepository để tìm sản phẩm theo ID
        Optional<Product> productOptional = productRepository.findById(productId);

        // Kiểm tra xem sản phẩm có tồn tại không
        if (productOptional.isPresent()) {
            return productOptional.get(); // Trả về sản phẩm nếu tồn tại
        } else {
            // Xử lý trường hợp sản phẩm không tồn tại, có thể throw exception hoặc trả về null tùy theo yêu cầu của bạn
            throw new ProductNotFoundException("Product with ID " + productId + " not found");
            // Hoặc return null
            // return null;
        }
    }
}
