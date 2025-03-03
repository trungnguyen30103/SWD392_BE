package com.blindbox.service.impl;

import com.blindbox.model.Wishlist;
import com.blindbox.repository.WishlistRepository;
import com.blindbox.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WishlistServiceImpl implements WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;

    @Override
    public List<Wishlist> getAllWishlistItems() {
        return wishlistRepository.findAll();
    }

    @Override
    public Wishlist addProductToWishlist(Wishlist wishlist) {
        return wishlistRepository.save(wishlist);
    }

    @Override
    public Wishlist getWishlistItemById(Integer id) {
        Optional<Wishlist> wishlist = wishlistRepository.findById(id);
        return wishlist.orElse(null); // Nếu không tìm thấy sẽ trả về null
    }

    @Override
    public void removeProductFromWishlist(Integer id) {
        wishlistRepository.deleteById(id);
    }
}
