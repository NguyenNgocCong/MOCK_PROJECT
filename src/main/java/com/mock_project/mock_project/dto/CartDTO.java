package com.mock_project.mock_project.dto;

import java.util.Date;

public class CartDTO {
    private Long id;
    private Date createdDate;
    private Long userId;

    public Long getId() {
        return id;
    }
    public Date getCreatedDate() {
        return createdDate;
    }
    public Long getUserId() {
        return userId;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
}