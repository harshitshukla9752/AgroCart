package com.agrocart.web.dto;

import com.agrocart.web.model.Cart;
import lombok.Data;

import java.util.List;

@Data
public class OrderRequestDTO {
    private List<Cart> items;
    private String address;
    private double totalAmount;
}
