package com.bitzu.demo.controllers;

import com.bitzu.demo.models.Tag;
import com.bitzu.demo.models.User;
import com.bitzu.demo.repositories.TagRepository;
import com.bitzu.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class TagController {
    @Autowired
    private TagRepository tagRepository;

    @GetMapping("/tag")
    public List<Tag> getTags() {
        return tagRepository.findAll();
    }
    @PostMapping("/tag/create")
    public ResponseEntity<Tag> createUser(@RequestBody Tag tag) {
        tagRepository.save(tag);
        return ResponseEntity.ok(tagRepository.save(tag));
    }
}
