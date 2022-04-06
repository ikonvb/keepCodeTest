package com.konstantinbulygin.onlinestore.model.restmodel;

import com.konstantinbulygin.onlinestore.model.Role;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
public class NewOutletUser {

    private Integer outUserId;
    private String outUserName;
    private String outUserPassword;
    private LocalDateTime outUserDate;
    private Set<Role> roles;

    public NewOutletUser() {
    }

    public NewOutletUser(Integer outUserId, String outUserName, String outUserPassword, LocalDateTime outUserDate, Set<Role> roles) {
        this.outUserId = outUserId;
        this.outUserName = outUserName;
        this.outUserPassword = outUserPassword;
        this.outUserDate = outUserDate;
        this.roles = roles;
    }
}
