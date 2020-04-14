package com.spring.onetoone;

import com.spring.onetoone.model.Gender;
import com.spring.onetoone.model.User;
import com.spring.onetoone.model.UserProfile;
import com.spring.onetoone.repository.UserProfileRepository;
import com.spring.onetoone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class OneToOneApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(OneToOneApplication.class, args);
    }

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserProfileRepository userProfileRepository;

    @Override
    public void run(String... args) throws Exception {


        //clean up database
        userProfileRepository.deleteAllInBatch();
        userRepository.deleteAllInBatch();


        //create user
        User user = new User("Rajriya", "Patel", "ria@ielts.com", "PASSWORD");
        User user1 = new User("Rajeev", "Patel", "ria@ielts.com", "PASSWORD");

        // Create a UserProfile instance
        UserProfile userProfile = new UserProfile("91-8197882053", Gender.MALE, "747", "2nd Cross", "Golf View Road, Kodihalli", "Bangalore",
                "Karnataka", "India", "560008");

        // Set parent reference(user) in child entity(userProfile)
        userProfile.setUser(user);

        // Set child reference(userProfile) in parent entity(user)
        user.setUserProfile(userProfile);

        // Save Parent Reference (which will save the child as well)
        userRepository.save(user);
        userRepository.save(user1);
    }
}
