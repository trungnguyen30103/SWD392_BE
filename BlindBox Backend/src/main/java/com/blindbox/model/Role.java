package com.blindbox.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "role")
public class Role {

    @Id

    private Integer roleID;

    @Column(name = "name", nullable = false, length = 50)
    private String roleName;


    @OneToMany(mappedBy = "role")
    private Set<User> user;

}
