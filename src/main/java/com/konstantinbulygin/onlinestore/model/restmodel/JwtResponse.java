package com.konstantinbulygin.onlinestore.model.restmodel;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class JwtResponse {

    private String token;
    private final String type = "Bearer";
    private Integer id;
    private String userName;
    private String userEmil;
    private List<String> roles;

    public JwtResponse(String token, Integer id, String userName, String userEmil, List<String> roles) {
        this.token = token;
        this.id = id;
        this.userName = userName;
        this.userEmil = userEmil;
        this.roles = roles;
    }
}
