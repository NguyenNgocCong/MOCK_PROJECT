package com.mock_project.mock_project.dto;

public class AddressDTO {
    private Long id;
    private String address;
    private Long userId;

    public AddressDTO() {
    }

    public Long getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public Long getUserId() {
        return userId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
}
