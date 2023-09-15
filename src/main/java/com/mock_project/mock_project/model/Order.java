package com.mock_project.mock_project.model;

import com.mock_project.mock_project.dto.Recipient;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.security.Timestamp;

@Entity
@Table(name = "\"order\"")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;
    private Timestamp deliveryTime;
    private float totalPrice;

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

    public Timestamp getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Timestamp deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public float getTotalPrice() {
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


    @OneToOne(cascade = CascadeType.ALL)
    private Recipient recipient; // Sử dụng CascadeType.ALL để đồng bộ hóa thay đổi thông tin người nhận

    public void setRecipient(Recipient recipient) {
        this.recipient = recipient;
    }
}
