package com.blindbox.service;

import com.blindbox.model.Cart;

public interface CartService {

    /* Cart */

    void addProductToCart(Integer userID, Integer productID, int quantity);

    void removeProductFromCart(Integer cartID, Integer productID);

    void clearCart(Integer userID);


    Cart getCartByUserID(Integer userID);


    /* Cart Item */

    void updateCartItem(Integer cartID, Integer productID, int quantity);
}
