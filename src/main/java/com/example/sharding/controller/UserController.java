package com.example.sharding.controller;

import java.util.Map;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sharding.model.User_Full;
import com.example.sharding.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    

    @PostMapping()
    public User_Full saveUser(@RequestBody Map<String,Object> userMap) {
        return null;
        
    }

    @GetMapping("/{id}")
    public User_Full getUser(@PathVariable int id) {
        return null;
        
    }
    
}
