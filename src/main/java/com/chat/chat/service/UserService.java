package com.chat.chat.service;


import com.chat.chat.domain.User;
import com.chat.chat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void signUp(User user) {
        Optional<User> byEmail = userRepository.findByEmail(user.getEmail());
        if (byEmail.isEmpty()) {
            userRepository.save(user);
        }
    }
}
