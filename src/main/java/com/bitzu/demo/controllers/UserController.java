package com.bitzu.demo.controllers;

import com.bitzu.demo.models.User;
import com.bitzu.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class UserController{
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/usuario")
    public List<User> getUsers() {
        return userRepository.findAll();
    }
    @PostMapping("usuario/create")
    public ResponseEntity createUser(@RequestBody User user) {
        userRepository.save(user);
        return ResponseEntity.ok(userRepository.save(user));
    }
}

