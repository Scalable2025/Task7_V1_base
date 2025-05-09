package com.example.sharding.model.user_details;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users_details")
public class User_Details {

    @Id
    private int id;
    private String email;
    private String password;

    

}
