package com.blindbox.model;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;


    @Data
    @Entity
    @Table(name = "customer_support")
    public class CustomerSupport {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long supportID;
        private Long userID;
        private Long orderID;
        private String message;
        private String status;
        private LocalDateTime createdAt;
    }

