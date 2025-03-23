package com.blindbox.controller;

import com.blindbox.model.Role;
import com.blindbox.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Role Management System", description = "Operations pertaining to roles in the Role Management System")
@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    // Lấy tất cả vai trò
    @Operation(summary = "Get all roles", description = "Retrieve a list of all available roles")
    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = roleService.getAllRoles();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    // Lấy vai trò theo ID
    @Operation(summary = "Get a role by ID", description = "Retrieve a single role using its ID")
    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable Integer id) {
        Role role = roleService.getRoleById(id);
        if (role != null) {
            return new ResponseEntity<>(role, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Tạo mới vai trò
    @Operation(summary = "Create a new role", description = "Add a new role to the system")
    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        Role createdRole = roleService.createRole(role);
        return new ResponseEntity<>(createdRole, HttpStatus.CREATED);
    }

    // Cập nhật vai trò
    @Operation(summary = "Update an existing role", description = "Update an existing role using its ID")
    @PutMapping("/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable Integer id, @RequestBody Role role) {
        Role updatedRole = roleService.updateRole(id, role);
        if (updatedRole != null) {
            return new ResponseEntity<>(updatedRole, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Xóa vai trò
    @Operation(summary = "Delete a role by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Integer id) {
        if (roleService.deleteRole(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
