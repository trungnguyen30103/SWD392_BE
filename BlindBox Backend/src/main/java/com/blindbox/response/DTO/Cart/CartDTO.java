package com.blindbox.response.DTO.Cart;

import com.blindbox.model.Cart;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.stream.Collectors;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartDTO {
    Integer userID;
    double totalAmount;
    List<CartItemDTO> cartItemDTOS;

    public CartDTO (Cart cart) {
        this.userID = cart.getUser().getUserID();
        this.totalAmount = cart.getTotalAmount();
        this.cartItemDTOS = cart.getCartItems().stream()
                .map(CartItemDTO::new)
                .collect(Collectors.toList());
    }
}
