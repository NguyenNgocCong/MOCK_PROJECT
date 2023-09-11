package com.mock_project.mock_project.controller;

import com.mock_project.mock_project.dto.ProductDTO;
import com.mock_project.mock_project.dto.ProductDetailDTO;
import com.mock_project.mock_project.mapper.ProductMapper;
import com.mock_project.mock_project.model.Product;
import com.mock_project.mock_project.service.CategoryService;
import com.mock_project.mock_project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductMapper productMappers;

    // API thêm sản phẩm mới
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        // Kiểm tra xem productDTO có categoryId hợp lệ không
        if (productDTO.getCategoryId() == null) {
            return ResponseEntity.badRequest().build();
        }

        // Tạo sản phẩm mới bằng ProductService
        Product createdProduct = productService.createProduct(productDTO);

        if (createdProduct != null) {
            ProductDTO createdProductDTO = productMappers.toProductDTO(createdProduct);
            return new ResponseEntity<>(createdProductDTO, HttpStatus.CREATED);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    // API cập nhật thông tin sản phẩm
    @PutMapping("/{productId}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long productId, @RequestBody ProductDTO updatedProductDTO) {
        // Triển khai logic để cập nhật thông tin sản phẩm
        // Sau khi cập nhật, trả về sản phẩm đã được cập nhật dưới dạng DTO
        return null;
    }

    // API xóa sản phẩm
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        // Triển khai logic để xóa sản phẩm
        // Trả về thông báo xóa thành công
        return null;
    }
    @GetMapping("/{productId}")
    public ResponseEntity<ProductDetailDTO> getProductDetail(@PathVariable Long productId) {
        ProductDetailDTO productDetailDTO = productService.getProductDetailById(productId);
        if (productDetailDTO != null) {
            return ResponseEntity.ok(productDetailDTO);
        }
        return ResponseEntity.notFound().build();
    }
}
