package com.konstantinbulygin.onlinestore.service;

import com.konstantinbulygin.onlinestore.model.User;
import com.konstantinbulygin.onlinestore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRepoService {

    @Autowired
    UserRepository userRepository;


    public User findByUserName(String username) {
        return userRepository.findByUserName(username);
    }

    public boolean existsByUserName(String outUserName) {
        return userRepository.existsByUserName(outUserName);
    }

    public void save(User user) {
        userRepository.save(user);
    }
}
