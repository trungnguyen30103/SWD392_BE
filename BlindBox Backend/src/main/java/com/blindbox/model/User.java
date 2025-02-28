package com.blindbox.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userID;
    private String userName;
    private String password;
    private Integer phone;
    private String address;
    private String email;
    private String role;
    private String fullName;
    private LocalDateTime createdAt;
    private String status;
    private LocalDateTime updatedAt;
}
