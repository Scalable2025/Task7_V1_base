package com.example.sharding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.sharding.model.user_name.User_Name;

@Repository
public interface UserRepository1 extends JpaRepository<User_Name, Integer> {

}
