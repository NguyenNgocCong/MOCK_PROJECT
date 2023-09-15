package com.mock_project.mock_project.controller;

import com.mock_project.mock_project.dto.CartLineItemRequest;
import com.mock_project.mock_project.dto.CreateOrderRequest;
import com.mock_project.mock_project.model.Address; // Import lớp Address
import com.mock_project.mock_project.model.Order;
import com.mock_project.mock_project.model.CartLineItem;
import com.mock_project.mock_project.model.Product;
import com.mock_project.mock_project.service.OrderService;
import com.mock_project.mock_project.service.ProductNotFoundException;
import com.mock_project.mock_project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService, ProductService productService) {
        this.orderService = orderService;
        this.productService = productService;
    }

    private final ProductService productService;

    @PostMapping("/create-order")
    public ResponseEntity<String> createOrder(@RequestBody CreateOrderRequest request) {
        try {
            // Kiểm tra xem Cart có sản phẩm không
            if (request.getCartItems().isEmpty()) {
                return ResponseEntity.badRequest().body("Cart is empty. Cannot create an order.");
            }
            // Kiểm tra và xác thực thông tin địa chỉ giao hàng, người nhận, thời gian giao hàng, vv.
            validateShippingInformation(request);

            // Tạo danh sách CartLineItem từ danh sách CartLineItemRequest
            List<CartLineItem> cartItems = convertToCartLineItems(request.getCartItems());

            // Tính toán tổng giá tiền từ Cart
            BigDecimal totalPrice = calculateTotalPrice(cartItems);

            // Tạo đối tượng Order
            Order order = new Order();
            order.setShippingAddress(request.getShippingAddress());
            order.setRecipient(request.getRecipient());
            order.setDeliveryTime(request.getDeliveryTime());
            order.setTotalPrice(totalPrice);

            // Lưu Order vào cơ sở dữ liệu
            orderService.saveOrder(order);

            // Xóa toàn bộ Cart Line Item hiện có (xóa mềm)
            clearCartItems(cartItems);

            return ResponseEntity.ok("Order created successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
        } catch (ProductNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void validateShippingInformation(CreateOrderRequest request) {
        // Xác thực thông tin địa chỉ giao hàng, người nhận, thời gian giao hàng, vv. ở đây
        Address shippingAddress = request.getShippingAddress();
        if (shippingAddress == null || shippingAddress.getAddress() == null || shippingAddress.getAddress().isEmpty()) {
            throw new IllegalArgumentException("Shipping address is invalid.");
        }
        // Kiểm tra các thông tin khác
    }

    private List<CartLineItem> convertToCartLineItems(List<CartLineItemRequest> requestItems) throws ProductNotFoundException {
        List<CartLineItem> cartLineItems = new ArrayList<>();
        for (CartLineItemRequest requestItem : requestItems) {
            Product product = productService.getProductById(requestItem.getProductId());
            if (product != null) {
                CartLineItem cartLineItem = new CartLineItem();
                cartLineItem.setProduct(product);
                cartLineItem.setQuantity(requestItem.getQuantity());
                cartLineItems.add(cartLineItem);
            }
        }
        return cartLineItems;
    }

    private BigDecimal calculateTotalPrice(List<CartLineItem> cartItems) {
        // Thực hiện tính tổng giá tiền từ danh sách Cart Line Item
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (CartLineItem item : cartItems) {
            totalPrice = totalPrice.add(item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
        }
        return totalPrice;
    }

    private void clearCartItems(List<CartLineItem> cartItems) {
        // Xóa mềm các Cart Line Item (đánh dấu chúng là đã xóa)
        for (CartLineItem item : cartItems) {
            item.setDeleted(true);
        }
    }
}
