package com.blindbox.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "blindbox_image")
public class BlindboxImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blindbox_image_id")
    private Integer blindboxImageID;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "blindbox_id", nullable = false)
    private Blindbox blindbox;

    @Column(name = "image_url", nullable = false, length = 500) // Định nghĩa độ dài tối đa
    private String imageUrl;

    @Column(name = "alt_text")
    private String altText;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
