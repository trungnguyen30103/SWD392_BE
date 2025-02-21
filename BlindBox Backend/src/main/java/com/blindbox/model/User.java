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
    private Long userID;
    private String userName;
    private String password;
    private String phone;
    private String address;
    private String email;
    private String role;
    private String firstName;
    private String lastName;
    private LocalDateTime createdAt;
    private String status;
    private LocalDateTime updatedAt;
}