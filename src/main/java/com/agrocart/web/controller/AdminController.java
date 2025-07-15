package com.agrocart.web.controller;

import com.agrocart.web.dto.ProductRequestDTO;
import com.agrocart.web.model.Product;
import com.agrocart.web.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final ProductService productService;

    public AdminController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAll());
    }

    @PostMapping("/products")
    public ResponseEntity<Product> addProduct(@RequestBody ProductRequestDTO dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setDescription(dto.getDescription());
        product.setCategory(dto.getCategory());
        product.setImageUrl(dto.getImageUrl());
        product.setStock(dto.getStock());

        Product saved = productService.save(product);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable String id, @RequestBody ProductRequestDTO dto) {
        Product updated = new Product();
        updated.setName(dto.getName());
        updated.setPrice(dto.getPrice());
        updated.setDescription(dto.getDescription());
        updated.setCategory(dto.getCategory());
        updated.setImageUrl(dto.getImageUrl());
        updated.setStock(dto.getStock());

        Product result = productService.updateProduct(id, updated);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable String id) {
        productService.delete(id);
        return ResponseEntity.ok("Product deleted");
    }
}
