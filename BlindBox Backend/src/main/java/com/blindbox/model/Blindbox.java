package com.blindbox.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "blindbox")
public class Blindbox {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blindbox_id")
    private Integer blindboxID;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private Double price;

    private Byte rating;

    @Column(nullable = false)
    private Integer stock;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime lastUpdated;


    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

<<<<<<< HEAD
=======
    // Connect Blindbox and Blindbox_image
    @OneToMany(mappedBy = "blindbox", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BlindboxImage> blindboxImages;

    // Tự động cập nhật thời gian
>>>>>>> 3ce307c8d5da7a526da900cc48537fd49c86ff5c
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.lastUpdated = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.lastUpdated = LocalDateTime.now();
    }
}
