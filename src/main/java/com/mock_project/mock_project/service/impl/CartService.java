package com.mock_project.mock_project.service.impl;
import com.mock_project.mock_project.model.Cart;
import com.mock_project.mock_project.model.CartLineItem;
import com.mock_project.mock_project.model.Product;
import com.mock_project.mock_project.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository; // Đảm bảo rằng bạn đã triển khai CartRepository

    public Cart getCart() {
        // Thực hiện xử lý để lấy giỏ hàng từ cơ sở dữ liệu
        // Sử dụng cartRepository để truy vấn cơ sở dữ liệu và lấy thông tin giỏ hàng
        Long userId = null;
        return cartRepository.findByUserId(null);
    }

    public CartLineItem addOrUpdateCartItem(Cart cart, Product product, int quantity) {
        // Kiểm tra xem sản phẩm đã có trong giỏ hàng chưa
        CartLineItem existingItem = findCartItemByProduct(cart, product);

        CartLineItem newCartItem = null;
        if (existingItem != null) {
            // Nếu sản phẩm đã có trong giỏ hàng, cập nhật số lượng
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
        } else {
            // Nếu sản phẩm chưa có trong giỏ hàng, tạo một CartLineItem mới
            newCartItem = new CartLineItem();
            newCartItem.setProduct(product);
            newCartItem.setQuantity(quantity);
            cart.getCartItems().add(newCartItem);
        }

        // Cập nhật tổng giá tiền của giỏ hàng
        updateCartTotalPrice(cart);

        // Lưu giỏ hàng vào cơ sở dữ liệu
        cartRepository.save(cart);

        return existingItem != null ? existingItem : newCartItem;
    }

    public void removeCartItem(Cart cart, Product product) {
        // Tìm và xóa CartLineItem của sản phẩm khỏi giỏ hàng
        CartLineItem itemToRemove = findCartItemByProduct(cart, product);
        if (itemToRemove != null) {
            cart.getCartItems().remove(itemToRemove);
            updateCartTotalPrice(cart);
            cartRepository.save(cart);
        }
    }

    public void clearCart(Cart cart) {
        // Xóa tất cả CartLineItem trong giỏ hàng
        cart.getCartItems().clear();
        updateCartTotalPrice(cart);
        cartRepository.save(cart);
    }

    private CartLineItem findCartItemByProduct(Cart cart, Product product) {
        // Tìm CartLineItem của sản phẩm trong giỏ hàng (nếu có)
        return cart.getCartItems().stream()
                .filter(item -> item.getProduct().equals(product))
                .findFirst()
                .orElse(null);
    }

    private void updateCartTotalPrice(Cart cart) {
        // Tính toán tổng giá tiền của giỏ hàng dựa trên thông tin các CartLineItem
        BigDecimal total = cart.getCartItems().stream()
                .map(item -> item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        cart.setTotalPrice(total);
    }

    public BigDecimal calculateTotalPrice(Cart cart) {
        BigDecimal totalPrice = BigDecimal.ZERO;

        // Lặp qua các mặt hàng trong giỏ hàng và tính tổng giá tiền
        for (CartLineItem item : cart.getCartItems()) {
            BigDecimal itemPrice = item.getProduct().getPrice();
            int quantity = item.getQuantity();
            BigDecimal lineTotal = itemPrice.multiply(BigDecimal.valueOf(quantity));
            totalPrice = totalPrice.add(lineTotal);
        }

        return totalPrice;
    }

    public void saveCart(Cart cart) {
        // Sử dụng cartRepository để lưu giỏ hàng vào cơ sở dữ liệu
        cartRepository.save(cart);
    }

}