package com.blindbox.service;

import com.blindbox.model.User;
import com.blindbox.request.Create.User.Admin.UserCreateRequest;
import com.blindbox.request.Create.User.Customer.CustomerCreateRequest;
import com.blindbox.request.Update.User.Admin.UserUpdateRequest;
import com.blindbox.request.Update.User.Customer.CustomerUpdateRequest;
import org.springframework.lang.NonNull;

import java.util.List;

public interface UserService {

    /* Admin */
    @NonNull
    User createAdmin(@NonNull UserCreateRequest request);

    @NonNull
    User createUser_Admin(@NonNull UserCreateRequest request);

    @NonNull
    User updateUser(@NonNull Integer userID, @NonNull UserUpdateRequest request);

    boolean deleteUser(Integer id);

    List<User> getAllUsers();

    User getUserById(Integer id);

    List<User> getUserByRoleID(Integer roleID);

    List<User> searchUserByUserName(@NonNull String userName);

    List<User> searchUserByFullName(@NonNull String fullName);

    List<User> searchUserByPhone(@NonNull String phone);

    List<User> searchUserByEmail(@NonNull String email);


    /* Customer/User */
    @NonNull
    User createCustomer(@NonNull CustomerCreateRequest request);

    @NonNull
    User updateCustomer(@NonNull Integer userID, @NonNull CustomerUpdateRequest request);

    User addBalanceToCustomer(Integer userID, int amount);

    @NonNull
    User forgotPassword (@NonNull Integer userID, @NonNull String newPassword);

}
