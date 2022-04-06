package com.konstantinbulygin.onlinestore.repository;

import com.konstantinbulygin.onlinestore.model.Role;
import com.konstantinbulygin.onlinestore.model.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByRoleName(RoleEnum roleEnum);
}
