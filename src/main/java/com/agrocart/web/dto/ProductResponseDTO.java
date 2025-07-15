package com.agrocart.web.dto;

import com.agrocart.web.model.Product;
import lombok.Data;

@Data
public class ProductResponseDTO {
    private String id;
    private String name;
    private String description;
    private double price;
    private String category;
    private String imageUrl;

    public ProductResponseDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.category = product.getCategory();
        this.imageUrl = product.getImageUrl();
    }
    // Getters and Setters
}