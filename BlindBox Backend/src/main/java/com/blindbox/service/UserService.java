package com.blindbox.service;

import com.blindbox.model.User;
import com.blindbox.request.Create.User.UserCreateRequest;
import com.blindbox.request.Update.User.UserUpdateRequest;
import org.springframework.lang.NonNull;

import java.util.List;

public interface UserService {

    /* Admin */
    @NonNull
    User createAdmin(@NonNull UserCreateRequest request);

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
    User createCustomer(@NonNull UserCreateRequest request);

}
