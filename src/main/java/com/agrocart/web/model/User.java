package com.agrocart.web.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "users")
public class User {
    private String id; 
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role = "USER"; // USER or ADMIN
    private String phone;
    private Address address;

    // Getters and Setters

}
