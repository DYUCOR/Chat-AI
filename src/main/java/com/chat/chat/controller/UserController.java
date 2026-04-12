package com.chat.chat.controller;


import com.chat.chat.domain.User;
import com.chat.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<Void> signUp(@RequestBody User user) {
        userService.signUp(user);
        return ResponseEntity.ok().build();
    }
}
