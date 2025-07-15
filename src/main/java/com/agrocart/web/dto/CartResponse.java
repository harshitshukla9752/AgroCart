package com.agrocart.web.dto;

import lombok.*;

@Data
@Builder
public class CartResponse {
    private String productId;
    private int quantity;
    private ProductDTO product;
}
