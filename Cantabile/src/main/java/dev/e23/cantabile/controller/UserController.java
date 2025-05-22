package dev.e23.cantabile.controller;


import dev.e23.cantabile.mapper.UserMapper;
import dev.e23.cantabile.model.User;
import jakarta.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Resource
    private UserMapper userMapper;

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers(){
        return userMapper.getAllUsers();
    }
}
