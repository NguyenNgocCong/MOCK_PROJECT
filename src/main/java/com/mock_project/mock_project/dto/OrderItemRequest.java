package com.mock_project.mock_project.dto;

import java.math.BigDecimal;

public class OrderItemRequest {

    private Long productId; // ID của sản phẩm
    private int quantity; // Số lượng sản phẩm trong đơn hàng
    private BigDecimal price; // Giá của sản phẩm

    // Constructors, getters, and setters

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}

