package com.agrocart.web.dto;

import com.agrocart.web.model.Address;
import lombok.Data;

@Data
public class ProfileDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Address address;

    public ProfileDTO() {}

    public ProfileDTO( String firtsName, String lastName, String email, String phone, Address address) {
        this.firstName = firtsName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    // Getters and Setters
}
