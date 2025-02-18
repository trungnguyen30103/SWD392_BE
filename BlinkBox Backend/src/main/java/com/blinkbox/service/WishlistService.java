package com.blinkbox.service;

import com.blinkbox.model.Wishlist;
import java.util.List;

public interface WishlistService {
    List<Wishlist> getAllWishlists();
    List<Wishlist> getWishlistsByUserId(Long userId);
    Wishlist getWishlistById(Long id);
    Wishlist createWishlist(Wishlist wishlist);
    void deleteWishlist(Long id);
}
