package com.blindbox.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer roleID;

    @Column(name = "name", nullable = false, length = 50)
    private String name;  // Tên của vai trò, không được null

    // Quan hệ giữa Role và User (Many-to-Many)
    @JsonIgnore
    @OneToMany(mappedBy = "role")  // Liên kết với quan hệ ManyToMany trong lớp User
    private Set<User> user;  // Một role có thể có nhiều user

}
