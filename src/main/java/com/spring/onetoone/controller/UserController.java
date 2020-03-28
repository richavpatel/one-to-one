package com.spring.onetoone.controller;

import com.spring.onetoone.model.User;
import com.spring.onetoone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    UserRepository userRepository;


    @GetMapping()
    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    @GetMapping("/{userId}")
    public  User getById(@PathVariable Long userId){
        return userRepository.findById(userId).get();
    }

    @PostMapping()
        public User createUser(@RequestBody  User user){
            return userRepository.save(user);
    }
    @PutMapping("/{userId}")
    public User updateUser(@PathVariable Long userId, @RequestBody User user){
        user.setId(userId);
        return userRepository.save(user);
    }

    @DeleteMapping("/{userId)")
    public void deleteUser(@PathVariable Long userId){
        userRepository.deleteById(userId);
    }

}
