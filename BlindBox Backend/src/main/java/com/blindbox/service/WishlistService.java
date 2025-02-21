package com.blindbox.service;

import com.blindbox.model.Wishlist;
import java.util.List;

public interface WishlistService {
    List<Wishlist> getAllWishlists();
    List<Wishlist> getWishlistsByUserId(Integer userId);
    Wishlist getWishlistById(Integer id);
    Wishlist createWishlist(Wishlist wishlist);
    void deleteWishlist(Integer id);
}
