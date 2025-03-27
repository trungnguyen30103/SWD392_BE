package com.blindbox.request.Create.User.Customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerCreateRequest {

    @NotBlank
    String userName;

    @NotBlank
    String password;

    @NotBlank
    String phone;

    @NotBlank
    String address;

    @Email
    @NotBlank
    String email;

    @NotBlank
    String fullName;

}
