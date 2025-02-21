package com.blindbox.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "wishlist")
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer wishlistId;

    @ManyToOne
    @JoinColumn(name = "userID", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "blindboxID", nullable = true)
    private Blindbox blindbox;

    @ManyToOne
    @JoinColumn(name = "productID", nullable = true)
    private Product product;
}
