package com.mock_project.mock_project.mapper;

import com.mock_project.mock_project.dto.VariantProductDTO;
import com.mock_project.mock_project.model.VariantProduct;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VariantProductMapper {
    @Mapping(source = "id", target = "productId")
    VariantProductDTO toVariantProductDTO(VariantProduct variantProduct);
    List<VariantProductDTO> toVariantProductDTOList(List<VariantProduct> variantProducts);
    // Ánh xạ ngược (nếu cần)
    @Mapping(source = "variantProducts", target = "variantProductsDTO")
    VariantProduct toVariantProduct(VariantProductDTO variantProductDTO);
}