package com.konstantinbulygin.onlinestore.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "outlet_users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "out_user_name")
})
public class OutletUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "out_user_id")
    Integer outUserId;

    @Email
    @NotNull
    @NotEmpty
    @Column(name = "out_user_name")
    String outUserName;

    @NotNull
    @NotEmpty
    @Size(min = 4, max = 8, message = "Password is mandatory")
    @Column(name = "out_user_password")
    String outUserPassword;

    @DateTimeFormat
    @Column(name = "out_user_date")
    LocalDateTime outUserDate;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "out_user_roles", joinColumns = @JoinColumn(name = "out_user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public OutletUser() {
    }

    public OutletUser(String outUserName, String outUserPassword, LocalDateTime outUserDate) {
        this.outUserName = outUserName;
        this.outUserPassword = outUserPassword;
        this.outUserDate = outUserDate;
    }
}
