package com.blindbox.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@Entity
@Getter
@Setter
@Table(name = "blindbox")
public class Blindbox {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blindbox_id")
    private Integer blindboxID;

    @Column(nullable = false)
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

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "blindbox", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("blindbox") // Prevents infinite loop
    private Set<BlindboxImage> blindboxImages = new CopyOnWriteArraySet<>();

    @OneToMany(mappedBy = "blindbox", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("blindbox") // Prevents infinite loop
    private Set<BlindBoxItem> blindBoxItems = new CopyOnWriteArraySet<>();

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
