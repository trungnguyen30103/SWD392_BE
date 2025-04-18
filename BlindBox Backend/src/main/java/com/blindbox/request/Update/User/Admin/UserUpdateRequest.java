package com.blindbox.request.Update.User.Admin;

import jakarta.validation.constraints.Email;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {

    String password;
    String phone;
    String address;
    String fullName;

    @Email
    String email;

    double balance;

    String avatar_url;


}
