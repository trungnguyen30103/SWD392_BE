package com.blindbox.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "blindbox_item")
public class BlindBoxItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blindbox_item_id")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "rarity", nullable = false)
    private Integer rarity;

    // Thay đổi từ BigDecimal thành double
    @Column(name = "price", nullable = false)
    private double price;
}
