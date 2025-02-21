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

    @GetMapping
    public List<Wishlist> getAllWishlists() {
        return wishlistService.getAllWishlists();
    }

    @GetMapping("/user/{userID}")
    public List<Wishlist> getWishlistsByUserId(@PathVariable Integer userId) {
        return wishlistService.getWishlistsByUserId(userId);
    }

    @GetMapping("/{id}")
    public Wishlist getWishlistById(@PathVariable Integer id) {
        return wishlistService.getWishlistById(id);
    }

    @PostMapping
    public Wishlist createWishlist(@RequestBody Wishlist wishlist) {
        return wishlistService.createWishlist(wishlist);
    }

    @DeleteMapping("/{id}")
    public void deleteWishlist(@PathVariable Integer id) {
        wishlistService.deleteWishlist(id);
    }
}
