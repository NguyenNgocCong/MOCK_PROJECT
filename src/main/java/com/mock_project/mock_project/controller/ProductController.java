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

import java.util.List;

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
        // Gọi phương thức createProduct trong ProductService để tạo sản phẩm
        Product createdProduct = productService.createProduct(productDTO);

        // Chuyển đổi sản phẩm đã tạo thành ProductDTO để trả về cho người dùng
        ProductDTO createdProductDTO = new ProductDTO();
        createdProductDTO.setId(createdProduct.getId());
        createdProductDTO.setName(createdProduct.getName());
        createdProductDTO.setCategoryId(createdProduct.getCategory().getId()); // Đặt categoryId từ sản phẩm đã tạo
        // Trả về ResponseEntity với thông tin sản phẩm đã tạo và mã trạng thái HTTP 201 (Created)
        return ResponseEntity.status(201).body(createdProductDTO);
    }

    // API cập nhật thông tin sản phẩm
    @PutMapping("/{productId}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long productId, @RequestBody ProductDTO updatedProductDTO) {
        // Gọi ProductService để cập nhật thông tin sản phẩm
        Product updatedProduct = productService.updateProduct(productId, updatedProductDTO);

        if (updatedProduct != null) {
            // Nếu sản phẩm được cập nhật thành công, trả về thông tin sản phẩm đã cập nhật
             updatedProductDTO = productMappers.toProductDTO(updatedProduct);
            return new ResponseEntity<>(updatedProductDTO, HttpStatus.OK);
        } else {
            // Nếu không tìm thấy sản phẩm hoặc có lỗi xảy ra, trả về lỗi 404
            return ResponseEntity.notFound().build();
        }
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
        // Gọi ProductService để lấy thông tin chi tiết sản phẩm theo ID
        ProductDetailDTO productDetailDTO = productService.getProductDetailById(productId);

        if (productDetailDTO != null) {
            return new ResponseEntity<>(productDetailDTO, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/productByCategory/{categoryId}")
    public List<Product> getProductsByCategory(
            @PathVariable Long categoryId,
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        return productService.getProductsByCategory(categoryId, pageNo, pageSize);
    }
}
