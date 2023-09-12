package com.mock_project.mock_project.service.impl;

import com.mock_project.mock_project.dto.VariantProductDTO;
import com.mock_project.mock_project.model.Product;
import com.mock_project.mock_project.model.VariantProduct;
import com.mock_project.mock_project.repository.ProductRepository;
import com.mock_project.mock_project.repository.VariantProductRepository;
import com.mock_project.mock_project.service.VariantProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VariantProductServiceImpl implements VariantProductService {
    @Autowired
    private VariantProductRepository variantProductRepository;
    @Autowired
    private ProductRepository productRepository;
    @Override
    public VariantProduct createVariantProduct(VariantProductDTO variantProductDTO) {
        // Tạo một đối tượng VariantProduct từ DTO
        VariantProduct variantProduct = new VariantProduct();
        variantProduct.setColor(variantProductDTO.getColor());
        variantProduct.setSize(variantProductDTO.getSize());
        variantProduct.setModel(variantProductDTO.getModel());
        variantProduct.setPrice(variantProductDTO.getPrice());
        // Lấy sản phẩm gốc (Product) từ cơ sở dữ liệu bằng productId
        Product product = productRepository.findById(variantProductDTO.getProductId()).orElse(null);
        if (product != null) {
            // Thiết lập quan hệ giữa sản phẩm biến thể và sản phẩm gốc
            variantProduct.setProduct(product);
            // Lưu sản phẩm biến thể vào cơ sở dữ liệu
            return variantProductRepository.save(variantProduct);
        } else {
            // Xử lý lỗi nếu sản phẩm gốc không tồn tại
            return null;
        }
    }

    @Override
    public VariantProduct updateVariantProduct(Long variantProductId, VariantProductDTO updatedVariantProductDTO) {
        // Kiểm tra xem sản phẩm biến thể có tồn tại trong cơ sở dữ liệu hay không
        VariantProduct existingVariantProduct = variantProductRepository.findById(variantProductId).orElse(null);
        if (existingVariantProduct != null) {
            // Cập nhật thông tin sản phẩm biến thể dựa trên dữ liệu từ updatedVariantProductDTO
            existingVariantProduct.setColor(updatedVariantProductDTO.getColor());
            existingVariantProduct.setSize(updatedVariantProductDTO.getSize());
            existingVariantProduct.setModel(updatedVariantProductDTO.getModel());
            existingVariantProduct.setPrice(updatedVariantProductDTO.getPrice());
            Product product = productRepository.findById(updatedVariantProductDTO.getProductId()).orElse(null);
            // Lưu sản phẩm biến thể đã cập nhật vào cơ sở dữ liệu
            if (product != null) {
                // Thiết lập quan hệ giữa sản phẩm biến thể và sản phẩm gốc
                existingVariantProduct.setProduct(product);
                // Lưu sản phẩm biến thể vào cơ sở dữ liệu
                return variantProductRepository.save(existingVariantProduct);
            } else {
                // Xử lý lỗi nếu sản phẩm biến thể không tồn tại
                return null;
            }
        }else {
            System.out.println("Khong tim thay product");
            return null;
        }
    }
    @Override
    public void deleteVariantProduct(Long variantProductId) {

    }
    public List<VariantProductDTO> getVariantProductsByProductId(Long productId) {
        List<VariantProduct> variantProducts = variantProductRepository.findByProductId(productId);
        return variantProducts.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    private VariantProductDTO convertToDTO(VariantProduct variantProduct) {
        VariantProductDTO dto = new VariantProductDTO();
        dto.setId(variantProduct.getId());
        dto.setColor(variantProduct.getColor());
        dto.setSize(variantProduct.getSize());
        dto.setModel(variantProduct.getModel());
        dto.setPrice(variantProduct.getPrice());
        return dto;
    }
}
