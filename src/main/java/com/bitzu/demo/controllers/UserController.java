package com.bitzu.demo.controllers;

import com.bitzu.demo.models.User;
import com.bitzu.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController{
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/usuario")
    public List<User> getUsers() {
        return userRepository.findAll();
    }
    @GetMapping("/usuario/email/{email}")
    public Optional<User> getUserByEmail(@PathVariable("email") String email) {
        return userRepository.findByEmail(email);
    }
    @PostMapping("usuario/create")
    public ResponseEntity createUser(@RequestBody User user) {
        userRepository.save(user);
        return ResponseEntity.ok(userRepository.save(user));
    }
}

