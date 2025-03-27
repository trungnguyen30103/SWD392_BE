package com.blindbox.response;


import lombok.Data;

@Data

public class AuthResponse {
    private String token;
    private String role;
    private Integer userID;

    public AuthResponse(String token, String role, Integer userID) {
        this.token = token;
        this.role = role;
        this.userID = userID;
    }
}
