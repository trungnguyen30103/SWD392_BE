package com.blindbox.controller;

import com.blindbox.model.CartItem;
import com.blindbox.service.impl.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    // API để thêm sản phẩm vào giỏ hàng
    @PostMapping("/add/{cartId}/{productId}")
    public CartItem addProductToCart(@PathVariable Integer cartId,
                                     @PathVariable Integer productId,
                                     @RequestParam int quantity) {
        return cartService.addProductToCart(cartId, productId, quantity);
    }
}
