package com.konstantinbulygin.onlinestore.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "user_name")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    Integer userId;

    @NotNull
    @NotEmpty
    @Column(name = "user_name")
    String userName;

    @NotNull
    @NotEmpty
    @Column(name = "user_password")
    @Size(max = 255)
    String userPassword;

    @DateTimeFormat
    @Column(name = "user_date")
    LocalDateTime userDate;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(String userName, String userPassword, LocalDateTime userDate) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userDate = userDate;
    }
}
