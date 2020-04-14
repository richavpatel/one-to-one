package com.spring.onetoone.controller;

import com.spring.onetoone.model.User;
import com.spring.onetoone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("users")

public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping()
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Cacheable(cacheNames = "user", key = "#userId")
    @GetMapping("/{userId}")
    public User getById(@PathVariable Long userId) {
        User user = userRepository.findById(userId).get();
        System.out.println(user);
        return user;
    }

    @GetMapping(params = {"firstName"})
    public User findByName(@RequestParam String firstName) {
        return userRepository.findByFirstName(firstName);
    }

    @GetMapping(params = {"firstName", "lastName"})
    public User findByName(@RequestParam String firstName, @RequestParam String lastName) {
        return userRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    @PostMapping()
    public User createUser(@RequestBody @Valid User user) {
        return userRepository.save(user);
    }

    @CachePut(cacheNames = "user", key = "#userId")
    @PutMapping("/{userId}")
    public User updateUser(@PathVariable Long userId, @RequestBody User user) {
        user.setId(userId);
        return userRepository.save(user);
    }

    @CacheEvict(cacheNames = "user", key = "#userId")
    @DeleteMapping("/{userId)")
    public void deleteUser(@PathVariable Long userId) {
        userRepository.deleteById(userId);
    }

}
