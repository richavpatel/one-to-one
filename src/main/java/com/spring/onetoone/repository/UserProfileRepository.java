package com.spring.onetoone.repository;

import com.spring.onetoone.model.User;
import com.spring.onetoone.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

    UserProfile findAllByUserId(Long userId);
}
