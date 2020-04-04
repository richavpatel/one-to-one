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

    @GetMapping(params = {"firstName"})
    public  User findByName(@RequestParam String firstName){
        return userRepository.findByFirstName(firstName);
    }
    @GetMapping(params = {"firstName", "lastName"})
    public  User findByName(@RequestParam String firstName,@RequestParam String lastName){
        return userRepository.findByFirstNameAndLastName(firstName, lastName);
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
