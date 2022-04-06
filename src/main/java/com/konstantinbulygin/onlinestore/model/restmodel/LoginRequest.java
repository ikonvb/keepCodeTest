package com.konstantinbulygin.onlinestore.model.restmodel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

    private String userName;
    private String userPassword;

    public LoginRequest(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public LoginRequest() {
    }
}
