package com.spring.onetoone.controller;


import com.spring.onetoone.ResourceNotFoundExecption.ResourceNotFoundException;
import com.spring.onetoone.model.User;
import com.spring.onetoone.model.UserProfile;
import com.spring.onetoone.repository.UserProfileRepository;
import com.spring.onetoone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class UserProfileController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserProfileRepository userProfileRepository;


    @GetMapping("/users/{userId}/userProfile")
    public UserProfile getUserProfileByUser(@PathVariable Long userId) {

        return userProfileRepository.findAllByUserId(userId);
    }

    @PostMapping("/users/{userId}/userProfile")
    public UserProfile createUserProfile(@PathVariable Long userId, @RequestBody UserProfile userProfile) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("UserId Not found " + userId));
        userProfile.setUser(user);
        return userProfileRepository.save(userProfile);
    }

    @PutMapping("/users/{userId}/userProfile/{userProfileId}")
    public UserProfile updateUserProfile(@PathVariable Long userId, @PathVariable Long userProfileId, @RequestBody UserProfile userProfile) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("UserId Not Found: " + userId);
        }
        if (!userProfileRepository.existsById(userProfileId)) {
            throw new ResourceNotFoundException("UserId Not Found: " + userProfileId);
        }
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("UserId Not found " + userId));
        userProfile.setUser(user);
        return userProfileRepository.save(userProfile);
    }

    @DeleteMapping("userProfile/{userProfileId}")
    public void deleteUserProfile(@PathVariable Long userProfileId) {
        userProfileRepository.deleteById(userProfileId);
    }

}
