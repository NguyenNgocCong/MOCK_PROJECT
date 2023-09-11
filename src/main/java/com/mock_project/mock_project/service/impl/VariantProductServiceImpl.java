package com.mock_project.mock_project.service.impl;

import com.mock_project.mock_project.dto.VariantProductDTO;
import com.mock_project.mock_project.model.VariantProduct;
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
    @Override
    public VariantProduct createVariantProduct(VariantProductDTO variantProductDTO) {
        return null;
    }

    @Override
    public VariantProduct updateVariantProduct(Long variantProductId, VariantProductDTO updatedVariantProductDTO) {
        return null;
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
