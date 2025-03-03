package com.blindbox.controller;

import com.blindbox.model.Wishlist;
import com.blindbox.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    // Get all wishlist items
    @GetMapping
    public List<Wishlist> getAllWishlistItems() {
        return wishlistService.getAllWishlistItems();
    }

    // Add a new product to wishlist
    @PostMapping
    public Wishlist addProductToWishlist(@RequestBody Wishlist wishlist) {
        return wishlistService.addProductToWishlist(wishlist);
    }

    // Get wishlist item by ID
    @GetMapping("/{id}")
    public Wishlist getWishlistItemById(@PathVariable Integer id) {
        return wishlistService.getWishlistItemById(id);
    }

    // Remove product from wishlist
    @DeleteMapping("/{id}")
    public void removeProductFromWishlist(@PathVariable Integer id) {
        wishlistService.removeProductFromWishlist(id);
    }
}
