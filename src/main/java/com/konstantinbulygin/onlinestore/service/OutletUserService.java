package com.konstantinbulygin.onlinestore.service;

import com.konstantinbulygin.onlinestore.model.OutletUser;
import com.konstantinbulygin.onlinestore.repository.OutletUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OutletUserService {

    @Autowired
    OutletUserRepository outletUserRepository;

    public OutletUser findByUserName(String username) {
        return outletUserRepository.findByOutUserName(username);
    }
}
