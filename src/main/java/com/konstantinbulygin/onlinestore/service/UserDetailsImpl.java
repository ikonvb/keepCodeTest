package com.konstantinbulygin.onlinestore.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.konstantinbulygin.onlinestore.model.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class UserDetailsImpl implements UserDetails {

    private static final long serialVersionUID = 1L;

    private Integer userId;
    private String userName;
    @JsonIgnore
    private String userPassword;
    private LocalDateTime userDate;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Integer userId, String userName, String userPassword, LocalDateTime userDate, Collection<? extends GrantedAuthority> authorities) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userDate = userDate;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(User user) {

        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName().name()))
                .collect(Collectors.toList());

        return new UserDetailsImpl(
                user.getUserId(),
                user.getUserName(),
                user.getUserPassword(),
                user.getUserDate(),
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return userPassword;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
