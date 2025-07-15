package com.agrocart.web.dto;

import lombok.Data;

@Data
public class UserRequestDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;
    private AddressDTO address;

    

    // Getters and Setters
}
