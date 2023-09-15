package com.mock_project.mock_project.service;

import com.mock_project.mock_project.model.Order;

public interface OrderService {
    Order saveOrder(Order order);
    Order getOrderById(Long orderId);
}
