package com.blindbox.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "blindboximage")
public class BlindboxImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer blindboxImageID;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "blindbox_id", nullable = false)
    private Blindbox blindbox;

    @Column(nullable = false, length = 500) // Định nghĩa độ dài tối đa
    private String imageUrl;
}
