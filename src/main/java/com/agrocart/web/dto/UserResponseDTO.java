package com.agrocart.web.dto;

import lombok.Data;

@Data
public class UserResponseDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String role;

    private AddressDTO address;

    

    // Getters and Setters
}
