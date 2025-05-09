package com.example.sharding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.sharding.model.user_details.User_Details;

@Repository
public interface UserRepository2 extends JpaRepository<User_Details, Integer>  {

}
