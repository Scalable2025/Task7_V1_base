package com.example.sharding.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.example.sharding.model.User;
import com.example.sharding.repository.UserRepository1;
import com.example.sharding.repository.UserRepository2;

@Service
public class UserService {

        private UserRepository1 userRepository1;
        private UserRepository2 userRepository2;

        @Autowired
        public UserService(UserRepository1 userRepository1, UserRepository2 userRepository2) {
                this.userRepository1 = userRepository1;
                this.userRepository2 = userRepository2;
        }

        
    
        public User saveUser(User user) {
            if (user.getId() % 2 == 0) {
                return userRepository1.save(user);
            } else {
                return userRepository2.save(user);
            }
        
        }

        public void populate() {
            for (int i = 1; i <= 2000; i++) {
                User user = new User();
                user.setId(i);
                user.setName("User" + i);
                user.setEmail("user" + i + "@gmail.com");
                saveUser(user);
            }
        }


}
