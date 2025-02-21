package com.blindbox.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryID;

    @Column(nullable = false, unique = true) // Category name không được trùng lặp
    private String categoryName;

    @Column(columnDefinition = "TEXT") // Cho phép mô tả dài hơn
    private String description;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Blindbox> blindboxes;
}
