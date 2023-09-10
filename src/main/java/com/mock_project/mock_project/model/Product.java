package com.mock_project.mock_project.model;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @OneToMany(mappedBy = "product") // Quan hệ một-nhiều với VariantProduct
    private List<VariantProduct> variantProducts;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<VariantProduct> getVariantProducts() {
        return variantProducts;
    }

    public void setVariantProducts(List<VariantProduct> variantProducts) {
        this.variantProducts = variantProducts;
    }
}
