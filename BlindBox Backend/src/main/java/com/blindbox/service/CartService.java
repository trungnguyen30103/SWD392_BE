package com.blindbox.service;

import com.blindbox.response.DTO.Cart.CartDTO;

public interface CartService {

    /* Cart */

    void addProductToCart(Integer userID, Integer productID, int quantity);

    void removeProductFromCart(Integer userID, Integer productID);

    void clearCart(Integer userID);


    CartDTO getCartByUserID(Integer userID);


    /* Cart Item */

    void updateCartItem(Integer userID, Integer productID, int quantity);
}
