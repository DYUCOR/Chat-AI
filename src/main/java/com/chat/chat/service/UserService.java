package com.chat.chat.service;


import com.chat.chat.domain.User;
import com.chat.chat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


    public void signUp(String username, String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashPassword = encoder.encode(password);
        User user = new User(null, username, hashPassword);
        userRepository.save(user);
    }
}
