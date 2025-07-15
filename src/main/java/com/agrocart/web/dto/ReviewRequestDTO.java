package com.agrocart.web.dto;

import lombok.Data;

@Data
public class ReviewRequestDTO {
    private String productId;
    private String userId;
    private int rating;
    private String comment;
    // Getters and setters
}
