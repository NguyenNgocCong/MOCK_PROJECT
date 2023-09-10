package com.mock_project.mock_project.controller;

import com.mock_project.mock_project.dto.ProductDTO;
import com.mock_project.mock_project.dto.ProductDetailDTO;
import com.mock_project.mock_project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    // API thêm sản phẩm mới
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        // Triển khai logic để thêm sản phẩm vào cơ sở dữ liệu
        // Sau khi thêm, trả về sản phẩm đã được tạo dưới dạng DTO
        return null;
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
