package com.agrocart.web.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@Document("orders")
public class Order {
    @Id
    private String id;
    private String userId;
    private List<Cart> items;
    private double totalAmount;
    private String address;
    private String status;
    private Date orderDate;
}
