package com.blindbox.service.impl;

import com.blindbox.model.Cart;
import com.blindbox.model.CartItem;
import com.blindbox.model.Product;
import com.blindbox.repository.CartItemRepository;
import com.blindbox.repository.CartRepository;
import com.blindbox.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl {

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

        double price = product.getPrice();
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        cartItem.setPrice(price * quantity);

        return cartItemRepository.save(cartItem);
    }

    // Lấy giỏ hàng của người dùng
    public Cart getCart(Integer cartId) {
        return cartRepository.findById(cartId).orElse(null);
    }

    // Xóa sản phẩm khỏi giỏ hàng
    public void removeProductFromCart(Integer cartId, Integer cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(() -> new RuntimeException("Sản phẩm trong giỏ hàng không tồn tại"));
        if (!cartItem.getCart().getCartId().equals(cartId)) {
            throw new RuntimeException("Giỏ hàng không khớp");
        }
        cartItemRepository.delete(cartItem);
    }
}
