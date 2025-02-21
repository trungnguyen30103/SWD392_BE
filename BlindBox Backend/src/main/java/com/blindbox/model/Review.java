package com.blindbox.model;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reviewID;
    @ManyToOne
    @JoinColumn(name = "userID", referencedColumnName = "userID")
    private User user;
    @ManyToOne
    @JoinColumn(name = "blindboxID", referencedColumnName = "blindboxID", nullable = false)
    private Blindbox blindbox;

    @ManyToOne
    @JoinColumn(name = "productID", referencedColumnName = "productID", nullable = false)
    private Product product;

    private Integer rating;
    private String comment;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;



}
