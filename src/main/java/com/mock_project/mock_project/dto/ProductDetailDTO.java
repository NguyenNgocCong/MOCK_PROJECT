package com.mock_project.mock_project.dto;

import java.util.List;

public class ProductDetailDTO {
    private ProductDTO product;
    private CategoryDTO category;
    private List<VariantProductDTO> variantProducts;

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    public List<VariantProductDTO> getVariantProducts() {
        return variantProducts;
    }

    public void setVariantProducts(List<VariantProductDTO> variantProducts) {
        this.variantProducts = variantProducts;
    }
}
