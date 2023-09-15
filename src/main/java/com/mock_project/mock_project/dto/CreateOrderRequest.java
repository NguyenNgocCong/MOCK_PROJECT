package com.mock_project.mock_project.dto;

import com.mock_project.mock_project.model.Address;

import java.time.LocalDateTime;
import java.util.List;

public class CreateOrderRequest {

    private Long userId;
    private List<OrderItemRequest> orderItems;
    private Address shippingAddress;
    private LocalDateTime deliveryTime;
    private Recipient recipient;
    private List<CartLineItemRequest> cartItems;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<OrderItemRequest> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemRequest> orderItems) {
        this.orderItems = orderItems;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public LocalDateTime getDeliveryTime() {
        return deliveryTime;
    }



    public void setDeliveryTime(LocalDateTime deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Recipient getRecipient() {
        return recipient;
    }

    public void setRecipient(Recipient recipient) {
        this.recipient = recipient;
    }

    public List<CartLineItemRequest> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartLineItemRequest> cartItems) {
        this.cartItems = cartItems;
    }

    public Address createShippingAddress() {
        // Nếu cần thêm các trường thông tin khác của địa chỉ, hãy thêm ở đây
        return this.shippingAddress;
    }
}
