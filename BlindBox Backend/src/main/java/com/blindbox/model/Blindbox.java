package com.blindbox.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "blindbox")
public class Blindbox {

    @Id

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
