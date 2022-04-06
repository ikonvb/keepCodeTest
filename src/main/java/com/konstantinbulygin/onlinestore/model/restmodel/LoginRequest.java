package com.konstantinbulygin.onlinestore.model.restmodel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

    private String outUsername;
    private String password;

    public LoginRequest(String outUsername, String password) {
        this.outUsername = outUsername;
        this.password = password;
    }
}
