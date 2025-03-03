package com.blindbox.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "wishlist")
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wishlist_id")
    private Integer wishlistId;  // Unique identifier for the wishlist

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;  // Foreign key referring to the User table

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;  // Foreign key referring to the Product table

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blindbox_id", nullable = false)
    private Blindbox blindbox;  // Foreign key referring to the Blindbox table

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();  // Date and time when the product was added to the wishlist
}
