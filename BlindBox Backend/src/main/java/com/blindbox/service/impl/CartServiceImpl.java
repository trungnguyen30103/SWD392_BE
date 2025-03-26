package com.blindbox.service.impl;

import com.blindbox.model.Cart;
import com.blindbox.model.CartItem;
import com.blindbox.model.Product;
import com.blindbox.repository.CartItemRepository;
import com.blindbox.repository.CartRepository;
import com.blindbox.repository.ProductRepository;
import com.blindbox.service.CartService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    private final CartItemRepository cartItemRepository;

    private final ProductRepository productRepository;

    public CartServiceImpl(CartRepository cartRepository, CartItemRepository cartItemRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
    }

    // Add product to cart
    @Override
    public void addProductToCart(Integer userID, Integer productID, int quantity) {
        Cart cart = cartRepository.findByUser_UserID(userID)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Product product = productRepository.findById(productID)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Check if the item  already exists in cart
        boolean productExists = false;
        for (CartItem cartItem : cart.getCartItems()) {
            if (cartItem.getProduct().getProductID().equals(productID)) {
                // Update the quantity
                cartItem.setQuantity(cartItem.getQuantity() + quantity);
                cartItem.setPrice(calculateItemTotalAmount(cart.getCartId(), productID));
                cartItemRepository.save(cartItem);
                productExists = true;
                break;
            }
        }

        // If the item does not exist, add it to the cart
        if (!productExists) {
            CartItem item = new CartItem();
            item.setCart(cart);
            item.setQuantity(quantity);
            item.setProduct(product);
            item.setPrice(product.getPrice() * quantity);
            cartItemRepository.save(item);
            cart.getCartItems().add(item);
        }

        // Update the total amount of the cart
        cart.setTotalAmount(calculateCartTotalAmount(cart.getCartItems()));
        cartRepository.save(cart);
    }

    // Get User's cart
    @Override
    public Cart getCartByUserID(Integer userID) {
        return cartRepository.findByUser_UserID(userID).orElse(null);
    }

    // Remove a product from cart
    @Override
    public void removeProductFromCart(Integer cartID, Integer productID) {
        CartItem cartItem = cartItemRepository.findByCart_CartIdAndProduct_ProductID(cartID, productID)
                .orElseThrow(() -> new RuntimeException("Product not exists in your cart"));
        cartItemRepository.delete(cartItem);

        // Update the total amount of the cart
        Cart cart = cartRepository.findById(cartID)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
        cart.setTotalAmount(calculateCartTotalAmount(cart.getCartItems()));
        cartRepository.save(cart);
    }

    // Clear entire Cart
    @Override
    public void clearCart(Integer userID) {
        Cart cart = cartRepository.findByUser_UserID(userID)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
        cart.getCartItems().clear();
        cart.setTotalAmount(0.0);
        cartRepository.save(cart);

        List<CartItem> items = cartItemRepository.findByCart_CartId(cart.getCartId());
        cartItemRepository.deleteAll(items);
    }

    // Update Cart Item
    @Override
    public void updateCartItem(Integer cartID, Integer productID, int quantity) {
        Cart cart = cartRepository.findById(cartID)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        CartItem cartItem = cartItemRepository.findByCart_CartIdAndProduct_ProductID(cartID, productID)
                .orElseThrow(() -> new RuntimeException("Product not found in cart"));

        Product product = productRepository.findById(productID)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (quantity == 0) {
            cartItemRepository.delete(cartItem);
        } else {
            cartItem.setQuantity(quantity);
            cartItem.setPrice(product.getPrice() * quantity);
            cartItemRepository.save(cartItem);
        }
        List<CartItem> updatedCartItems = cartItemRepository.findByCart_CartId(cartID);
        cart.setTotalAmount(updatedCartItems.stream().mapToDouble(CartItem::getPrice).sum());
        cartRepository.save(cart);
    }

    /* Calculator */
    // Calculate Item Total Amount
    private Double calculateItemTotalAmount(Integer cartID, Integer productID) {
        Product product = productRepository.findById(productID)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        CartItem item = cartItemRepository.findByCart_CartIdAndProduct_ProductID(cartID, productID)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));
        return product.getPrice() * item.getQuantity();
    }

    // Calculate Cart Total Amount
    private Double calculateCartTotalAmount(List<CartItem> items) {
        return items.stream()
                .mapToDouble(CartItem::getPrice)
                .sum();
    }
}
