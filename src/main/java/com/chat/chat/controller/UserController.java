package com.chat.chat.controller;


import com.chat.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<Void> signUp(@RequestParam String username, @RequestParam String password) {
        userService.signUp(username, password);
        return ResponseEntity.ok().build();
    }
}
