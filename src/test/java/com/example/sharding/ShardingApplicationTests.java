package com.example.sharding;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.sharding.model.User_Full;
import com.example.sharding.repository.UserRepository1;
import com.example.sharding.repository.UserRepository2;

@SpringBootTest
class ShardingApplicationTests {
    @Value("${ID}")
    private String id;
    private static String url="http://localhost:"+"/users";

    @Autowired
    private UserRepository1 userRepository1;
    @Autowired
    private UserRepository2 userRepository2;
    @Autowired
    private RestTemplate restTemplate;

    

    @BeforeEach
    public void setUp() {
        userRepository1.deleteAll();
        userRepository2.deleteAll();
        url="http://localhost:"+id+"/users";
    }

    @Test
    public void testSaveUser() {
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("name", "John Doe");
        userMap.put("email", "john@example.com");
        userMap.put("phone", "1234567890");
        System.out.println(url);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(userMap, headers);

        ResponseEntity<User_Full> response = restTemplate.postForEntity(url, request, User_Full.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getName()).isEqualTo("John Doe");
    }

    @Test
    public void testGetUserById() {
        // First save a user (reuse the POST call logic or ensure this user exists)
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("name", "Jane Doe");
        userMap.put("email", "jane@example.com");
        userMap.put("phone", "0987654321");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(userMap, headers);
        ResponseEntity<User_Full> postResponse = restTemplate.postForEntity(url, request, User_Full.class);
        int userId = postResponse.getBody().getId();

        // Now test GET by ID
        ResponseEntity<User_Full> getResponse = restTemplate.getForEntity(url+"/" + userId, User_Full.class);

        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(getResponse.getBody()).isNotNull();
        assertThat(getResponse.getBody().getName()).isEqualTo("Jane Doe");
    }
}
