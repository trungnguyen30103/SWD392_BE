package com.blindbox.response.DTO.Cart;

import com.blindbox.model.CartItem;
import com.blindbox.response.DTO.Product.ProductDTO;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartItemDTO {

    int quantity;
    double price;
    ProductDTO productDTO;

    public CartItemDTO (CartItem item) {
        this.quantity = item.getQuantity();
        this.price = item.getPrice();
        this.productDTO = new ProductDTO(item.getProduct());
    }

}
