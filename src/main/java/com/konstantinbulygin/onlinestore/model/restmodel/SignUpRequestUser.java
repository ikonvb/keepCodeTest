package com.konstantinbulygin.onlinestore.model.restmodel;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class SignUpRequestUser {

    private String userName;
    private String userPassword;
    private Set<String> roles;

    public SignUpRequestUser() {
    }

    public SignUpRequestUser(String userName, String userPassword, Set<String> roles) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.roles = roles;
    }
}
