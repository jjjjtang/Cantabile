package dev.e23.cantabile.controller;


import dev.e23.cantabile.mapper.UserMapper;
import dev.e23.cantabile.model.LoginRequst;
import dev.e23.cantabile.model.User;
import jakarta.annotation.Resource;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UserController {

    @Resource
    private UserMapper userMapper;

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers(){
        return userMapper.getAllUsers();
    }

    @PostMapping("/login")
    public Integer login(@RequestBody LoginRequst loginRequst){
        User user = userMapper.login(loginRequst.getUsername(), loginRequst.getPassword());
        if(user != null){
            return user.getUserId();
        }
        return -1;
    }

    @GetMapping("/getUserById")
    public User getUserById(@RequestParam("userId") Integer userId){
        User user = userMapper.getUserById(userId);
        return user;
    }




}
