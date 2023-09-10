package com.mock_project.mock_project.service;

import com.mock_project.mock_project.dto.VariantProductDTO;
import com.mock_project.mock_project.model.VariantProduct;

public interface VariantProductService {
    VariantProduct createVariantProduct(VariantProductDTO variantProductDTO);
    VariantProduct updateVariantProduct(Long variantProductId, VariantProductDTO updatedVariantProductDTO);
    void deleteVariantProduct(Long variantProductId);
}
