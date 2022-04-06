package com.konstantinbulygin.onlinestore.repository;

import com.konstantinbulygin.onlinestore.model.OutletUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutletUserRepository extends JpaRepository<OutletUser, Integer> {
    OutletUser findByOutUserName(String username);
}
