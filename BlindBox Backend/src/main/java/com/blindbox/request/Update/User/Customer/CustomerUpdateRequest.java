package com.blindbox.request.Update.User.Customer;

import jakarta.validation.constraints.Email;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerUpdateRequest {

    String password;
    String phone;
    String address;
    String fullName;

    @Email
    String email;

    String avatar_url;

}
