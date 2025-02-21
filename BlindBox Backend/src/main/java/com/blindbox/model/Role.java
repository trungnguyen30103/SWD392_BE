package com.blindbox.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleID;

    private String roleName;

    // Quan hệ giữa Role và User
    @ManyToOne
    @JoinColumn(name = "userID", referencedColumnName = "userID", nullable = false)
    private User user;
}
