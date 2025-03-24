package com.blindbox.controller;

import com.blindbox.model.Cart;
import com.blindbox.model.CartItem;
import com.blindbox.service.impl.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    @Autowired
    private CartServiceImpl cartService;

    // Thêm sản phẩm vào giỏ hàng
    @PostMapping("/{cartId}/add/{productId}")
    public ResponseEntity<CartItem> addProductToCart(@PathVariable Integer cartId,
                                                     @PathVariable Integer productId,
                                                     @RequestParam int quantity) {
        try {
            CartItem cartItem = cartService.addProductToCart(cartId, productId, quantity);
            return ResponseEntity.ok(cartItem);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Lấy giỏ hàng của người dùng
    @GetMapping("/{cartId}")
    public ResponseEntity<Cart> getCart(@PathVariable Integer cartId) {
        Cart cart = cartService.getCart(cartId);
        if (cart != null) {
            return ResponseEntity.ok(cart);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Xóa sản phẩm khỏi giỏ hàng
    @DeleteMapping("/{cartId}/remove/{cartItemId}")
    public ResponseEntity<Void> removeProductFromCart(@PathVariable Integer cartId,
                                                      @PathVariable Integer cartItemId) {
        try {
            cartService.removeProductFromCart(cartId, cartItemId);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
