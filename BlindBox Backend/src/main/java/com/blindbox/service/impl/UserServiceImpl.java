package com.blindbox.service.impl;

import com.blindbox.model.Cart;
import com.blindbox.model.Role;
import com.blindbox.model.User;
import com.blindbox.repository.CartRepository;
import com.blindbox.repository.RoleRepository;
import com.blindbox.repository.UserRepository;
import com.blindbox.request.Create.User.Admin.UserCreateRequest;
import com.blindbox.request.Create.User.Customer.CustomerCreateRequest;
import com.blindbox.request.Update.User.Admin.UserUpdateRequest;
import com.blindbox.request.Update.User.Customer.CustomerUpdateRequest;
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
    private final CartRepository cartRepository;

    public UserServiceImpl (UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, CartRepository cartRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.cartRepository = cartRepository;
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

        // Set role ADMIN
        Role role = roleRepository.findById(1) // 1: Admin
                .orElseThrow(() -> new RuntimeException("Role not found"));
        admin.setRole(role);

        // Save
        return userRepository.save((admin));
    }

    @Override
    @NonNull
    public User createUser_Admin(@NonNull UserCreateRequest request) {
        User user = new User();
        user.setUserName(request.getUserName());
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        user.setPassword(encodedPassword);
        user.setPhone(request.getPhone());
        user.setAddress(request.getAddress());
        user.setEmail(request.getEmail());
        user.setFullName(request.getFullName());
        if (request.getBalance() != 0) user.setBalance(request.getBalance());
        user.setStatus("ACTIVE");

        // Set role CUSTOMER
        Role role = roleRepository.findById(2) // 1: Admin
                .orElseThrow(() -> new RuntimeException("Role not found"));
        user.setRole(role);

        // Save customer first to generate ID
        user = userRepository.save(user);

        // Create cart for the customer
        Cart cart = new Cart();
        cart.setUser(user);

        // Save cart in DB
        cartRepository.save(cart);

        // Save
        return user;
    }

    @Override
    @NonNull
    public User updateUser(@NonNull Integer userID, @NonNull UserUpdateRequest request) {
        User existingUser = userRepository.findById(userID)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Update
        if (request.getPassword() != null) {
            String encodedPassword = passwordEncoder.encode(request.getPassword());
            existingUser.setPassword(encodedPassword);
        }
        if (request.getPhone() != null) existingUser.setPhone(request.getPhone());
        if (request.getAddress() != null) existingUser.setAddress(request.getAddress());
        if (request.getEmail() != null) existingUser.setEmail(request.getEmail());
        if (request.getFullName() != null) existingUser.setFullName(request.getFullName());
        if (request.getBalance() != 0) existingUser.setBalance(request.getBalance());
        if (request.getAvatar_url() != null) existingUser.setAvatar_url(request.getAvatar_url());

        // Save
        return userRepository.save(existingUser);
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
    public User createCustomer(@NonNull CustomerCreateRequest request) {
        User customer = new User();
        customer.setUserName(request.getUserName());
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        customer.setPassword(encodedPassword);
        customer.setPhone(request.getPhone());
        customer.setAddress(request.getAddress());
        customer.setEmail(request.getEmail());
        customer.setFullName(request.getFullName());
        customer.setBalance(0);
        customer.setStatus("ACTIVE");

        // Set role CUSTOMER
        Role role = roleRepository.findById(2) // 2: Customer
                .orElseThrow(() -> new RuntimeException("Role not found"));
        customer.setRole(role);

        // Save customer first to generate ID
        customer = userRepository.save(customer);

        // Create cart for the customer
        Cart cart = new Cart();
        cart.setUser(customer);

        // Save cart in DB
        cartRepository.save(cart);

        return customer;
    }

    // Add balance
    @Override
    public User addBalanceToCustomer(Integer userID, int amount) {
        User user = userRepository.findById(userID)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getRole().getRoleID() != 2) { // 2: Customer
            throw new RuntimeException("User is not a customer");
        }

        user.setBalance(user.getBalance() + amount);
        return userRepository.save(user);
    }

    @Override
    @NonNull
    public User updateCustomer(@NonNull Integer userID, @NonNull CustomerUpdateRequest request) {
        User existingCustomer = userRepository.findByUserIDAndRole_RoleID(userID, 2) //2: Customer/User
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Update
        if (request.getPassword() != null) {
            String encodedPassword = passwordEncoder.encode(request.getPassword());
            existingCustomer.setPassword(encodedPassword);
        }
        if (request.getPhone() != null) existingCustomer.setPhone(request.getPhone());
        if (request.getAddress() != null) existingCustomer.setAddress(request.getAddress());
        if (request.getEmail() != null) existingCustomer.setEmail(request.getEmail());
        if (request.getFullName() != null) existingCustomer.setFullName(request.getFullName());
        if (request.getAvatar_url() != null) existingCustomer.setAvatar_url(request.getAvatar_url());

        // Save
        return userRepository.save(existingCustomer);
    }

    @Override
    @NonNull
    public User forgotPassword (@NonNull Integer userID, @NonNull String newPassword) {
        User forgotPasswordUser = userRepository.findById(userID)
                .orElseThrow(() -> new RuntimeException("User not found"));
        String encodedPassword = passwordEncoder.encode(newPassword);
        forgotPasswordUser.setPassword(encodedPassword);

        // Save
        return userRepository.save(forgotPasswordUser);
    }

}
