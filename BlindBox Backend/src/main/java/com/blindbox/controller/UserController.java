package com.blindbox.controller;

import com.blindbox.model.User;
import com.blindbox.request.Create.User.Admin.UserCreateRequest;
import com.blindbox.request.Create.User.Customer.CustomerCreateRequest;
import com.blindbox.request.Update.User.Admin.UserUpdateRequest;
import com.blindbox.request.Update.User.Customer.CustomerUpdateRequest;
import com.blindbox.response.ResponseData;
import com.blindbox.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "User Management System", description = "Operations pertaining to users in the User Management System")
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /* Admin
    * */

    // Create admin
    @Operation(summary = "Create a new admin", description = "Register a new user in the system")
    @PostMapping("/admin")
    public ResponseEntity<ResponseData> createAdmin(@Valid @RequestBody UserCreateRequest request) {
        try {
            User createdAdmin = userService.createAdmin(request);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponseData(201, true, "Admin created successfully", createdAdmin, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseData(500, false, "Failed to create admin", null, null));
        }
    }

    // Cập nhật thông tin người dùng
    @Operation(summary = "Update an existing user", description = "Update an existing user using their ID")
    @PutMapping("/admin/{userID}")
    public ResponseEntity<ResponseData> updateUser(@PathVariable Integer userID, @Valid @RequestBody UserUpdateRequest request) {
        try {
            User updatedCustomer = userService.updateUser(userID, request);
            return ResponseEntity.ok(new ResponseData(200, true, "Customer updated successfully", updatedCustomer, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseData(500, false, "Failed to update customer", null, null));
        }
    }

    /* Customer
    * */

    // Create customer
    @Operation(summary = "Create a new customer", description = "Add a new customer to the system")
    @PostMapping("/customer")
    public ResponseEntity<ResponseData> createCustomer(@Valid @RequestBody CustomerCreateRequest request) {
        try {
            User createdCustomer = userService.createCustomer(request);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponseData(201, true, "Customer created successfully", createdCustomer, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseData(500, false, "Failed to create customer", null, null));
        }
    }

    // Update customer
    @Operation(summary = "Update customer", description = "Update customer details by user ID")
    @PutMapping("/customer/{userID}")
    public ResponseEntity<ResponseData> updateCustomer(@PathVariable Integer userID, @RequestBody CustomerUpdateRequest request) {
        try {
            User updatedUser = userService.updateCustomer(userID, request);
            return ResponseEntity.ok(new ResponseData(200, true, "Customer updated successfully", updatedUser, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseData(500, false, "Failed to update customer", null, null));
        }
    }

    // Forgot Password
    @Operation(summary = "Forgot password", description = "Update password for user by user ID")
    @PutMapping("/forgot-password/{userID}")
    public ResponseEntity<ResponseData> forgotPassword(@PathVariable Integer userID, @RequestBody String newPassword) {
        try {
            User updatedUser = userService.forgotPassword(userID, newPassword);
            return ResponseEntity.ok(new ResponseData(200, true, "Password updated successfully", updatedUser, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseData(500, false, "Failed to update password", null, null));
        }
    }

    /* Chung
    * */

    // Xóa người dùng
    @Operation(summary = "Delete a user by ID")
    @DeleteMapping("/{userID}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer userID) {
        if (userService.deleteUser(userID)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    /* GET
    * */

    // Lấy tất cả người dùng
    @Operation(summary = "Get all users", description = "Retrieve a list of all registered users")
    @GetMapping
    public ResponseEntity<ResponseData> getAllUsers() {
        try {
            List<User> users = userService.getAllUsers();
            return ResponseEntity.ok(new ResponseData(200, true, "Users retrieved successfully", users, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseData(500, false, "Failed to retrieve users", null, null));
        }
    }

    // Lấy thông tin người dùng theo ID
    @Operation(summary = "Get a user by ID", description = "Retrieve a single user using their ID")
    @GetMapping("/{userID}")
    public ResponseEntity<User> getUserById(@PathVariable Integer userID) {
        User user = userService.getUserById(userID);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Search user(s) by roleID
    @Operation(summary = "Search users by user name", description = "Search for users using their user name")
    @GetMapping("/search/userName")
    public ResponseEntity<ResponseData> searchUserByUserName(@RequestParam String userName) {
        try {
            List<User> users = userService.searchUserByUserName(userName);
            if (users.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseData(404, false, "No users found with name '" + userName + "'.", null, null));
            }
            return ResponseEntity.ok(new ResponseData(200, true, "Users found", users, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseData(500, false, "Failed to search users", null, null));
        }
    }

    // Search user(s) by user name
    @Operation(summary = "Search users by roleID", description = "Search for users using roleID")
    @GetMapping("/search/role")
    public ResponseEntity<ResponseData> searchUserByUserName(@RequestParam Integer roleID) {
        try {
            List<User> users = userService.getUserByRoleID(roleID);
            if (users.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseData(404, false, "No users found with roleID '" + roleID + "'.", null, null));
            }
            return ResponseEntity.ok(new ResponseData(200, true, "Users found", users, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseData(500, false, "Failed to search users", null, null));
        }
    }

    // Search user(s) by full name
    @Operation(summary = "Search users by name", description = "Search for users using their name")
    @GetMapping("/search/fullName")
    public ResponseEntity<ResponseData> searchUserByFullName(@RequestParam String fullName) {
        try {
            List<User> users = userService.searchUserByFullName(fullName);
            if (users.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseData(404, false, "No users found with name '" + fullName + "'.", null, null));
            }
            return ResponseEntity.ok(new ResponseData(200, true, "Users found", users, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseData(500, false, "Failed to search users", null, null));
        }
    }

    // Search user(s) by phone number
    @Operation(summary = "Search users by phone", description = "Search for users using their phone number")
    @GetMapping("/search/phone")
    public ResponseEntity<ResponseData> searchUserByPhone(@RequestParam String phone) {
        try {
            List<User> users = userService.searchUserByPhone(phone);
            if (users.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseData(404, false, "No users found with phone '" + phone + "'.", null, null));
            }
            return ResponseEntity.ok(new ResponseData(200, true, "Users found", users, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseData(500, false, "Failed to search users", null, null));
        }
    }

    // Search user(s) by email
    @Operation(summary = "Search users by email", description = "Search for users using their email")
    @GetMapping("/search/email")
    public ResponseEntity<ResponseData> searchUserByEmail(@RequestParam String email) {
        try {
            List<User> users = userService.searchUserByEmail(email);
            if (users.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseData(404, false, "No users found with email '" + email + "'.", null, null));
            }
            return ResponseEntity.ok(new ResponseData(200, true, "Users found", users, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseData(500, false, "Failed to search users", null, null));
        }
    }

}
