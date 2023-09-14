package com.mock_project.mock_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mock_project.mock_project.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
