package com.blinkbox.model;

import jakarta.persistence.*;
import lombok.*;
import com.blinkbox.model.Category;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "blinkbox")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Blinkbox {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long blinkboxId;

    @Column(nullable = false)
    private String blinkboxName;

    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    private Double rating;

    @Column(nullable = false)
    private Integer stock;

    private LocalDateTime createdAt;
    private LocalDateTime lastUpdated;

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
