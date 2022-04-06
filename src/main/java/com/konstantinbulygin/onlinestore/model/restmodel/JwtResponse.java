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
    private String outUserName;
    private List<String> roles;

    public JwtResponse(String token, Integer id, String outUserName, List<String> roles) {
        this.token = token;
        this.id = id;
        this.outUserName = outUserName;
        this.roles = roles;
    }
}
