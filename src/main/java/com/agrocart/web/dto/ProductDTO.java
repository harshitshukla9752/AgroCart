package com.agrocart.web.dto;

import lombok.*;

@Data
@Builder
public class ProductDTO {
    private String id;
    private String name;
    private String image;
    private double price;
}
