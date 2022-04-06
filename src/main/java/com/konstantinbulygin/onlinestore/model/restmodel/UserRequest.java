package com.konstantinbulygin.onlinestore.model.restmodel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {

    private String userName;
    private String userPassword;

    public UserRequest(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public UserRequest() {
    }
}
