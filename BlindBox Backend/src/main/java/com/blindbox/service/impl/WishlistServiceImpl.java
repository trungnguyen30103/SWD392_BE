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
    public List<Wishlist> getAllWishlists() {
        return wishlistRepository.findAll();
    }

    @Override
    public List<Wishlist> getWishlistsByUserId(Integer userId) {
        return wishlistRepository.findByUserId(userId);
    }

    @Override
    public Wishlist getWishlistById(Integer id) {
        Optional<Wishlist> wishlist = wishlistRepository.findById(id);
        return wishlist.orElse(null);
    }

    @Override
    public Wishlist createWishlist(Wishlist wishlist) {
        return wishlistRepository.save(wishlist);
    }

    @Override
    public void deleteWishlist(Integer id) {
        wishlistRepository.deleteById(id);
    }
}
