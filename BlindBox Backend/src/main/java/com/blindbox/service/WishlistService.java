package com.blindbox.service;

import com.blindbox.model.Wishlist;

import java.util.List;

public interface WishlistService {
    List<Wishlist> getAllWishlistItems();
    Wishlist addProductToWishlist(Wishlist wishlist);
    Wishlist getWishlistItemById(Integer id);
    void removeProductFromWishlist(Integer id);
}
