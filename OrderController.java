package controller;
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create-order")
    public ResponseEntity<String> createOrder(@RequestBody CreateOrderRequest request) {
        try {
            // Kiểm tra xem Cart có sản phẩm không
            if (request.getCartItems().isEmpty()) {
                return ResponseEntity.badRequest().body("Cart is empty. Cannot create an order.");
            }

            // Tính toán tổng giá tiền từ Cart
            BigDecimal totalPrice = calculateTotalPrice(request.getCartItems());

            // Tạo đối tượng Order
            Order order = new Order();
            order.setShippingAddress(request.getShippingAddress());
            order.setRecipient(request.getRecipient());
            order.setDeliveryTime(request.getDeliveryTime());
            order.setTotalPrice(totalPrice);

            // Lưu Order vào cơ sở dữ liệu
            orderService.saveOrder(order);

            // Xóa toàn bộ Cart Line Item hiện có (xóa mềm)
            clearCartItems(request.getCartItems());

            return ResponseEntity.ok("Order created successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
        }
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
