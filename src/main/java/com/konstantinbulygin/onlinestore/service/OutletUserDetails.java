package com.konstantinbulygin.onlinestore.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.konstantinbulygin.onlinestore.model.OutletUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class OutletUserDetails implements UserDetails {

    private static final long serialVersionUID = 1L;

    private Integer outUserId;
    private String outUserName;
    @JsonIgnore
    private String outUserPassword;
    private LocalDateTime outUserDate;
    private Collection<? extends GrantedAuthority> authorities;

    public OutletUserDetails(Integer outUserId, String outUserEmail, String outUserPassword, LocalDateTime outUserDate, Collection<? extends GrantedAuthority> authorities) {
        this.outUserId = outUserId;
        this.outUserName = outUserEmail;
        this.outUserPassword = outUserPassword;
        this.outUserDate = outUserDate;
        this.authorities = authorities;
    }

    public static OutletUserDetails build(OutletUser outletUser) {

        List<GrantedAuthority> authorities = outletUser.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName().name()))
                .collect(Collectors.toList());

        return new OutletUserDetails(
                outletUser.getOutUserId(),
                outletUser.getOutUserName(),
                outletUser.getOutUserPassword(),
                outletUser.getOutUserDate(),
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return outUserPassword;
    }

    @Override
    public String getUsername() {
        return null;
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
