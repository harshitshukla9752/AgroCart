package com.agrocart.web.dto;

import lombok.Data;

@Data
public class LoginResponseDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String token;

    public LoginResponseDTO(String firstName,String lastName, String email, String token) {
        this.firstName = firstName; 
        this.lastName = lastName; 
        this.email = email;
        this.token = token;
    }

    // Getters and setters
}
