package com.example.sharding.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.example.sharding.model.User_Full;
import com.example.sharding.model.user_details.User_Details;
import com.example.sharding.model.user_name.User_Name;
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

        public User_Full saveUser(Map<String, Object> userMap) {
                // Create User_Name object with its name
                // Save it to the first database and get the generated ID
                // Create User_Details object with its email and password and the generated ID from last step
                // return the User_Full object
                return null;
        }
        public User_Full getUser(int id) {
                // Get the User_Name object and User_Details object from the two databases using the ID then join them


                return null;
                
        }

        
    
    
       


}
