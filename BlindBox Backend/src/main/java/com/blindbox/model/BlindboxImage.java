package com.blindbox.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "blindbox_image")
public class BlindboxImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long blindboxImageId;

    @ManyToOne
    @JoinColumn(name = "blindbox_id", nullable = false)
    private Blindbox blindbox;


    @Column(nullable = false)
    private String imageUrl;
}
