package com.konstantinbulygin.onlinestore.service;

import com.konstantinbulygin.onlinestore.model.Role;
import com.konstantinbulygin.onlinestore.model.RoleEnum;
import com.konstantinbulygin.onlinestore.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public Optional<Role> findByName(RoleEnum roleUser) {
        return roleRepository.findByRoleName(roleUser);
    }

    public void save(Role role) {
        roleRepository.save(role);
    }
}
