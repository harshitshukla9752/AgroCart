package com.agrocart.web.dto;

import lombok.Data;

@Data
public class UserRegisterDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    // Getters and Setters
}