package com.agrocart.web.repository;

import com.agrocart.web.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {

    // Find products by category (case-insensitive)
    List<Product> findByCategoryIgnoreCase(String category);

    // Find products with stock below a threshold
    List<Product> findByStockLessThan(int threshold);

    // Optional: Search by keyword in name or description
    List<Product> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String name, String description);
}
