package com.blindbox.model;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewID;
    private Long userID;
    private Long blindboxID;
    private Long productID;
    private Integer rating;
    private String comment;
    private LocalDateTime createdAt;
}
