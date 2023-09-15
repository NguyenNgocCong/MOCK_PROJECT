package com.mock_project.mock_project.controller;

import com.mock_project.mock_project.model.Cart;
import com.mock_project.mock_project.model.CartLineItem;
import com.mock_project.mock_project.model.Product;
import com.mock_project.mock_project.service.ProductNotFoundException;
import com.mock_project.mock_project.service.ProductService;
import com.mock_project.mock_project.service.impl.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    private final ProductService productService;

    public CartController(CartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }

    @PostMapping("/add-product")
    public ResponseEntity<Cart> addProductToCart(@RequestParam Long productId, @RequestParam int quantity) {
        try {
            // Kiểm tra xem sản phẩm tồn tại
            Product product = productService.getProductById(productId);
            if (product == null) {
                return ResponseEntity.badRequest().body(null);
            }

            // Kiểm tra xem giỏ hàng đã tồn tại
            Cart cart = cartService.getCart();
            if (cart == null) {
                cart = new Cart();
            }
//            // Kiểm tra xem giỏ hàng đã tồn tại
//            Optional<Cart> cartOptional = Optional.ofNullable(cartService.getCart());
//            Cart cart = cartOptional.orElseGet(() -> new Cart());

            // Tạo hoặc cập nhật Cart Line Item
            CartLineItem lineItem = cartService.addOrUpdateCartItem(cart, product, quantity);

            // Tính toán tổng giá tiền của giỏ hàng
            BigDecimal totalPrice = cartService.calculateTotalPrice(cart);

            // Lưu giỏ hàng
            cartService.saveCart(cart);

            return ResponseEntity.ok(cart);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        } catch (ProductNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

