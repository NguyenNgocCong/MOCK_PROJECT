package com.mock_project.mock_project.mapper;

import com.mock_project.mock_project.dto.VariantProductDTO;
import com.mock_project.mock_project.model.VariantProduct;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VariantProductMapper {
    VariantProductDTO toVariantProductDTO(VariantProduct variantProduct);
    List<VariantProductDTO> toVariantProductDTOList(List<VariantProduct> variantProducts);
    // Ánh xạ ngược (nếu cần)
    VariantProduct toVariantProduct(VariantProductDTO variantProductDTO);
}