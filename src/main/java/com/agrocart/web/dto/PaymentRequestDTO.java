package com.agrocart.web.dto;

import lombok.Data;

@Data
public class PaymentRequestDTO {
    private int amount;
    private String userEmail;
    // Getters and Setters
}
