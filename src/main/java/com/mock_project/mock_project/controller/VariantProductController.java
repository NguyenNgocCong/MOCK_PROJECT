package com.mock_project.mock_project.controller;

import com.mock_project.mock_project.dto.VariantProductDTO;
import com.mock_project.mock_project.mapper.VariantProductMapper;
import com.mock_project.mock_project.model.VariantProduct;
import com.mock_project.mock_project.service.VariantProductService;
import org.springframework.http.HttpStatus;
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
    @Autowired
    private VariantProductMapper variantProductMapper;
    // API thêm sản phẩm biến thể mới
    @PostMapping
    public ResponseEntity<VariantProductDTO> createVariantProduct(@RequestBody VariantProductDTO variantProductDTO) {
        // Gọi VariantProductService để tạo sản phẩm biến thể
        VariantProduct createdVariantProduct = variantProductService.createVariantProduct(variantProductDTO);

        if (createdVariantProduct != null) {
            // Chuyển đổi sản phẩm biến thể đã tạo thành DTO và trả về trong ResponseEntity
            VariantProductDTO createdVariantProductDTO = variantProductMapper.toVariantProductDTO(createdVariantProduct);
            return new ResponseEntity<>(createdVariantProductDTO, HttpStatus.CREATED);
        } else {
            // Xử lý lỗi nếu cần
            return ResponseEntity.badRequest().build();
        }
    }

    // API cập nhật thông tin sản phẩm biến thể
    @PutMapping("/{variantProductId}")
    public ResponseEntity<VariantProductDTO> updateVariantProduct(@PathVariable Long variantProductId, @RequestBody VariantProductDTO updatedVariantProductDTO) {
        // Gọi Service để cập nhật thông tin sản phẩm biến thể
        VariantProduct updatedVariantProduct = variantProductService.updateVariantProduct(variantProductId, updatedVariantProductDTO);

        if (updatedVariantProduct != null) {
            // Chuyển đổi sản phẩm biến thể đã cập nhật thành DTO và trả về trong ResponseEntity
             updatedVariantProductDTO = variantProductMapper.toVariantProductDTO(updatedVariantProduct);
            System.out.println("Thành Công");
            return ResponseEntity.ok(updatedVariantProductDTO);
        } else {
            // Xử lý lỗi nếu sản phẩm biến thể không tồn tại
            return ResponseEntity.notFound().build();
        }
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
