package com.blinkbox.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "wishlist")
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wishlistId;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "blinkboxId", nullable = true)
    private Blinkbox blinkbox;

    @ManyToOne
    @JoinColumn(name = "productId", nullable = true)
    private Product product;
}
