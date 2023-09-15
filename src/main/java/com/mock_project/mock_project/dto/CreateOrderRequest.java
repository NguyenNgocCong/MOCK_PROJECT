package com.mock_project.mock_project.dto;

import java.time.LocalDateTime;
import java.util.List;

public class CreateOrderRequest {

    private Long userId; // ID của người dùng đặt hàng (nếu cần)
    private List<OrderItemRequest> orderItems; // Danh sách các mặt hàng trong đơn hàng
    private String shippingAddress; // Địa chỉ giao hàng
    private String recipientName; // Tên người nhận
    private LocalDateTime deliveryTime; // Thời gian giao hàng (nếu cần)
    private Recipient recipient;

    public Recipient getRecipient() {
        return recipient;
    }

    // Constructors, getters, and setters

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

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public LocalDateTime getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(LocalDateTime deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    // Các trường và phương thức khác của CreateOrderRequest

    private List<CartLineItemRequest> cartItems;

    public List<CartLineItemRequest> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartLineItemRequest> cartItems) {
        this.cartItems = cartItems;
    }

    private String recipientAddress;
    private String recipientPhoneNumber;

    public String getRecipientAddress() {
        return recipientAddress;
    }

    public void setRecipientAddress(String recipientAddress) {
        this.recipientAddress = recipientAddress;
    }

    public String getRecipientPhoneNumber() {
        return recipientPhoneNumber;
    }

    public void setRecipientPhoneNumber(String recipientPhoneNumber) {
        this.recipientPhoneNumber = recipientPhoneNumber;
    }


    public void setRecipient(Recipient recipient) {
        this.recipient = recipient;
    }
}

