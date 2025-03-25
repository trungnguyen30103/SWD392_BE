package com.blindbox.service.impl;

import com.blindbox.model.Role;
import com.blindbox.model.User;
import com.blindbox.repository.RoleRepository;
import com.blindbox.repository.UserRepository;
import com.blindbox.request.Create.User.UserCreateRequest;
import com.blindbox.request.Update.User.UserUpdateRequest;
import com.blindbox.service.UserService;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl (UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /* Admin
    * */

    @Override
    @NonNull
    public User createAdmin(@NonNull UserCreateRequest request) {
        User admin = new User();
        admin.setUserName(request.getUserName());
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        admin.setPassword(encodedPassword);
        admin.setPhone(request.getPhone());
        admin.setAddress(request.getAddress());
        admin.setEmail(request.getEmail());
        admin.setFullName(request.getFullName());
        if (request.getBalance() != 0) admin.setBalance(request.getBalance());
        admin.setStatus("ACTIVE");

        // Update role
        Role role = roleRepository.findById(1) // 1: Admin
                .orElseThrow(() -> new RuntimeException("Role not found"));
        admin.setRole(role);

        // Save
        return userRepository.save((admin));
    }

    @Override
    public boolean deleteUser(Integer id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    @Override
    public List<User> getUserByRoleID(Integer roleID) {
        return userRepository.findByRole_RoleID(roleID);
    }

    @Override
    public List<User> searchUserByUserName(@NonNull String userName) {
        return userRepository.findByUserNameContainingIgnoreCase(userName);
    }

    @Override
    public List<User> searchUserByFullName(@NonNull String userName) {
        return userRepository.findByFullNameContainingIgnoreCase(userName);
    }

    @Override
    public List<User> searchUserByPhone(@NonNull String phone) {
        return userRepository.findByPhoneContainingIgnoreCase(phone);
    }

    @Override
    public List<User> searchUserByEmail(@NonNull String email) {
        return userRepository.findByEmailContainingIgnoreCase(email);
    }

    /* Customer/User
    * */

    @Override
    @NonNull
    public User createCustomer(@NonNull UserCreateRequest request) {
        User customer = new User();
        customer.setUserName(request.getUserName());
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        customer.setPassword(encodedPassword);
        customer.setPhone(request.getPhone());
        customer.setAddress(request.getAddress());
        customer.setEmail(request.getEmail());
        customer.setFullName(request.getFullName());
        if (request.getBalance() != 0) customer.setBalance(request.getBalance());
        customer.setStatus("ACTIVE");

        // Update role
        Role role = roleRepository.findById(2) // 2: Customer
                .orElseThrow(() -> new RuntimeException("Role not found"));
        customer.setRole(role);

        // Save
        return userRepository.save((customer));
    }

    @Override
    @NonNull
    public User updateUser(@NonNull Integer userID, @NonNull UserUpdateRequest request) {
        User existingUser = userRepository.findById(userID)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Update
        if (request.getUserName() != null) existingUser.setUserName(request.getUserName());
        if (request.getPassword() != null) existingUser.setPassword(request.getPassword());
        if (request.getPhone() != null) existingUser.setPhone(request.getPhone());
        if (request.getAddress() != null) existingUser.setAddress(request.getAddress());
        if (request.getEmail() != null) existingUser.setEmail(request.getEmail());
        if (request.getFullName() != null) existingUser.setFullName(request.getFullName());
        if (request.getBalance() != 0) existingUser.setBalance(request.getBalance());

        // Save
        return userRepository.save(existingUser);
    }

}
