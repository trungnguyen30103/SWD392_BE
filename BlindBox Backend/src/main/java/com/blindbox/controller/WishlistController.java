package com.blindbox.controller;

import com.blindbox.model.Wishlist;
import com.blindbox.service.WishlistService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Wishlist Management System", description = "Operations pertaining to wishlist items in the Wishlist Management System")
@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    // Get all wishlist items
    @Operation(summary = "Get all wishlist items", description = "Retrieve a list of all products in the wishlist")
    @GetMapping
    public List<Wishlist> getAllWishlistItems() {
        return wishlistService.getAllWishlistItems();
    }

    // Add a new product to wishlist
    @Operation(summary = "Add a product to wishlist", description = "Add a new product to the wishlist")
    @PostMapping
    public Wishlist addProductToWishlist(@RequestBody Wishlist wishlist) {
        return wishlistService.addProductToWishlist(wishlist);
    }

    // Get wishlist item by ID
    @Operation(summary = "Get a wishlist item by ID", description = "Retrieve a single wishlist item using its ID")
    @GetMapping("/{id}")
    public Wishlist getWishlistItemById(@PathVariable Integer id) {
        return wishlistService.getWishlistItemById(id);
    }

    // Remove product from wishlist
    @Operation(summary = "Remove a product from wishlist", description = "Remove a product from the wishlist using its ID")
    @DeleteMapping("/{id}")
    public void removeProductFromWishlist(@PathVariable Integer id) {
        wishlistService.removeProductFromWishlist(id);
    }
}
