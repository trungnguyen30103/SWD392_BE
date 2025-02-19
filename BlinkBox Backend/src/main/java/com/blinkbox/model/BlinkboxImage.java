package com.blinkbox.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "blinkbox_image")
public class BlinkboxImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long blinkboxImageId;

    @ManyToOne
    @JoinColumn(name = "blinkboxId", nullable = false)
    private Blinkbox blinkbox;

    @Column(nullable = false)
    private String imageUrl;
}
