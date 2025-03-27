package com.blindbox.model;

import com.blindbox.enums.Blindbox.BlindboxItemStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "blindbox_item")
public class BlindBoxItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blindbox_item_id")
    private Integer blindboxItemID;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "blindbox_id", nullable = false)
    @JsonIgnore // Prevents recursion
    private Blindbox blindbox;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer rarity;

    @Column(nullable = false)
    private BlindboxItemStatus status;

    @Column(nullable = false)
    private Integer stock;

    @Column(name = "image_url", nullable = false, length = 500)
    private String imageUrl;

}
