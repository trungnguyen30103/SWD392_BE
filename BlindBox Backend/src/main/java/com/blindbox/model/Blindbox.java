package com.blindbox.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;


@Data
@Entity
@Table(name = "blindboxes")
public class Blindbox {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long blindboxID  ;

    private String blindboxName;
    private String description;
    private Double price;
    private Long categoryID;
    private Double rating;
    private Integer stock;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdated;

}