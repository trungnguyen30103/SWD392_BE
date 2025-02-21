package com.blindbox.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "blindboximage")
public class BlindboxImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer blindboxImageId;

    @ManyToOne(cascade = CascadeType.ALL) // Đảm bảo ảnh bị xóa khi Blindbox bị xóa
    @JoinColumn(name = "blindboxID", referencedColumnName = "blindboxID", nullable = false)
    private Blindbox blindbox;

    @Column(nullable = false)
    private String imageUrl;
}
