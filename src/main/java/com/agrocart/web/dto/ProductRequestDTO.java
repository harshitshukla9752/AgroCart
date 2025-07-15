package com.agrocart.web.dto;

import lombok.Data;

@Data
public class ProductRequestDTO {
    private String name;
    private String description;
    private double price;
    private int stock;
    private String category;
    private String imageUrl;
    // Getters and Setters
}