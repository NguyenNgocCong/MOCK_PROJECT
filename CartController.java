package controller;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

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

            // Tạo hoặc cập nhật Cart Line Item
            CartLineItem lineItem = cartService.addOrUpdateCartItem(cart, product, quantity);

            // Tính toán tổng giá tiền của giỏ hàng
            BigDecimal totalPrice = cartService.calculateTotalPrice(cart);

            // Lưu giỏ hàng
            cart = cartService.saveCart(cart);

            return ResponseEntity.ok(cart);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
