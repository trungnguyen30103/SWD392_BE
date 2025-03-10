package com.blindbox.service.impl;

import com.blindbox.model.Cart;
import com.blindbox.model.CartItem;
import com.blindbox.model.Product;
import com.blindbox.repository.CartItemRepository;
import com.blindbox.repository.CartRepository;
import com.blindbox.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    // Thêm sản phẩm vào giỏ hàng
    public CartItem addProductToCart(Integer cartId, Integer productId, int quantity) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Giỏ hàng không tồn tại"));
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Sản phẩm không tồn tại"));

        BigDecimal price = product.getPrice();
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        cartItem.setPrice(price.multiply(new BigDecimal(quantity)));

        return cartItemRepository.save(cartItem);
    }
}
