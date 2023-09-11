package com.mock_project.mock_project.controller;

import com.mock_project.mock_project.dto.VariantProductDTO;
import com.mock_project.mock_project.service.VariantProductService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/variant-products")
public class VariantProductController {
    @Autowired
    private VariantProductService variantProductService;

    // API thêm sản phẩm biến thể mới
    @PostMapping
    public ResponseEntity<VariantProductDTO> createVariantProduct(@RequestBody VariantProductDTO variantProductDTO) {
        // Triển khai logic để thêm sản phẩm biến thể vào cơ sở dữ liệu
        // Sau khi thêm, trả về sản phẩm biến thể đã được tạo dưới dạng DTO
        return null;
    }

    // API cập nhật thông tin sản phẩm biến thể
    @PutMapping("/{variantProductId}")
    public ResponseEntity<VariantProductDTO> updateVariantProduct(@PathVariable Long variantProductId, @RequestBody VariantProductDTO updatedVariantProductDTO) {
        // Triển khai logic để cập nhật thông tin sản phẩm biến thể
        // Sau khi cập nhật, trả về sản phẩm biến thể đã được cập nhật dưới dạng DTO
        return null;
    }

    // API xóa sản phẩm biến thể
    @DeleteMapping("/{variantProductId}")
    public ResponseEntity<Void> deleteVariantProduct(@PathVariable Long variantProductId) {
        // Triển khai logic để xóa sản phẩm biến thể
        // Trả về thông báo xóa thành công
        return null;
    }
    @GetMapping("/{productId}/variant-products")
    public ResponseEntity<List<VariantProductDTO>> getVariantProductsByProductId(@PathVariable Long productId) {
        List<VariantProductDTO> variantProducts = variantProductService.getVariantProductsByProductId(productId);
        return ResponseEntity.ok(variantProducts);
    }
}
