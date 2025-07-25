package com.agrocart.web.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String id) {
        super("Product not found with ID: " + id);
    }
}
