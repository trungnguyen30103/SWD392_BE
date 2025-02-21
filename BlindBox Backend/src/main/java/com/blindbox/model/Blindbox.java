package com.blindbox.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "blindbox")
public class Blindbox {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer blindboxID;

    private String blindboxName;
    private String description;
    private Double price;
    private Byte rating;
    private Integer stock;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdated;

    // Thiết lập quan hệ với Category
    @ManyToOne
    @JoinColumn(name = "categoryID", referencedColumnName = "categoryID", nullable = false)
    private Category category;
}
