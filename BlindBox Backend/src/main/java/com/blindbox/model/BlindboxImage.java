package com.blindbox.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "blindbox_image") // Sử dụng snake_case cho chuẩn SQL
public class BlindboxImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blindbox_image_id")
    private Integer blindboxImageID;

    @ManyToOne(cascade = CascadeType.MERGE) // Tránh xóa nhầm Blindbox
    @JoinColumn(name = "blindbox_id", nullable = false)
    private Blindbox blindbox;

    @Column(nullable = false, length = 500) // Định nghĩa độ dài tối đa
    private String imageUrl;
}
