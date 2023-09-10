package com.mock_project.mock_project.service.impl;

import com.mock_project.mock_project.dto.VariantProductDTO;
import com.mock_project.mock_project.model.VariantProduct;
import com.mock_project.mock_project.repository.VariantProductRepository;
import com.mock_project.mock_project.service.VariantProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
