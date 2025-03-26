package com.blindbox.controller;

import com.blindbox.model.Cart;
import com.blindbox.response.ResponseData;
import com.blindbox.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Cart Management System", description = "Operations pertaining to carts in the Cart Management System")
@RestController
@RequestMapping("/api/carts")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @Operation(summary = "Add product to cart", description = "Add a product to the user's cart")
    @PostMapping("/{userId}/add/{productId}")
    public ResponseEntity<ResponseData> addProductToCart(@PathVariable Integer userId,
                                                         @PathVariable Integer productId,
                                                         @RequestParam int quantity) {
        try {
            cartService.addProductToCart(userId, productId, quantity);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponseData(201, true, "Product added to cart successfully", null, null));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseData(400, false, "Failed to add product to cart", null, null));
        }
    }

    @Operation(summary = "Get cart by user ID", description = "Retrieve the cart for a given user")
    @GetMapping("/user/{userId}")
    public ResponseEntity<ResponseData> getCartByUserID(@PathVariable Integer userId) {
        try {
            Cart cart = cartService.getCartByUserID(userId);
            return ResponseEntity.ok(new ResponseData(200, true, "Cart retrieved successfully", cart, null));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseData(404, false, "Cart not found", null, null));
        }
    }

    @Operation(summary = "Remove product from cart", description = "Remove a product from the user's cart")
    @DeleteMapping("/{cartId}/remove/{productId}")
    public ResponseEntity<ResponseData> removeProductFromCart(@PathVariable Integer cartId, @PathVariable Integer productId) {
        try {
            cartService.removeProductFromCart(cartId, productId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new ResponseData(204, true, "Product removed from cart", null, null));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseData(400, false, "Failed to remove product from cart", null, null));
        }
    }

    @Operation(summary = "Clear entire cart", description = "Clear all items from the user's cart")
    @DeleteMapping("/{userId}/clear")
    public ResponseEntity<ResponseData> clearCart(@PathVariable Integer userId) {
        try {
            cartService.clearCart(userId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new ResponseData(204, true, "Cart cleared successfully", null, null));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseData(400, false, "Failed to clear cart", null, null));
        }
    }

    @Operation(summary = "Update cart item", description = "Update the quantity and price of a cart item")
    @PutMapping("/{cartId}/update/{productId}")
    public ResponseEntity<ResponseData> updateCartItem(@PathVariable Integer cartId, @PathVariable Integer productId, @RequestParam int quantity) {
        try {
            cartService.updateCartItem(cartId, productId, quantity);
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new ResponseData(204, true, "Cart item updated successfully", null, null));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseData(400, false, "Failed to update cart item" + e.getMessage(), null, null));
        }
    }
}