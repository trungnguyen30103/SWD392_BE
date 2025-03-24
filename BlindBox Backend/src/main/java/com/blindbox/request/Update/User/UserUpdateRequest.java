package com.blindbox.request.Update.User;

import jakarta.validation.constraints.Email;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {

    String userName;
    String password;
    String phone;
    String address;
    String fullName;

    @Email
    String email;


}
