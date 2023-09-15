package com.mock_project.mock_project.model;

import com.mock_project.mock_project.dto.Recipient;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "\"order\"")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;
    private LocalDateTime deliveryTime;
    private BigDecimal totalPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(LocalDateTime deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @OneToOne(cascade = CascadeType.ALL) // Sử dụng CascadeType.ALL để đồng bộ hóa thay đổi địa chỉ giao hàng
    private Address shippingAddress;

    // Constructors, getters, và setters

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }


    @OneToOne
    @JoinColumn(name = "recipient_id") // Đây là khóa ngoại tham chiếu đến thông tin người nhận
    private Recipient recipient;

    public void setRecipient(Recipient recipient) {
        this.recipient = recipient;
    }
}
