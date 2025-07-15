package com.agrocart.web.controller;

import com.agrocart.web.dto.ProductRequestDTO;
import com.agrocart.web.dto.ProductResponseDTO;
import com.agrocart.web.model.Product;
import com.agrocart.web.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
public List<ProductResponseDTO> getAll() {
    return service.getAll().stream()
            .map(ProductResponseDTO::new)
            .toList(); // Java 16+ clean syntax
}


    @GetMapping("/{id}")
    public ProductResponseDTO getById(@PathVariable String id) {
        return service.getById(id)
                .map(ProductResponseDTO::new)
                .orElse(null);
    }

    @PostMapping
    public ProductResponseDTO create(@RequestBody ProductRequestDTO dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setDescription(dto.getDescription());
        product.setStock(dto.getStock());
        product.setCategory(dto.getCategory());
        product.setImageUrl(dto.getImageUrl());

        Product saved = service.save(product);
        return new ProductResponseDTO(saved);
    }

    @PostMapping("/products")
    public ProductResponseDTO addProduct(@RequestBody ProductRequestDTO dto) {
    Product product = new Product();
    product.setName(dto.getName());
    product.setPrice(dto.getPrice());
    Product saved = service.save(product);
        return new ProductResponseDTO(saved);
    }

    @PutMapping("/{id}")
    public ProductResponseDTO update(@PathVariable String id, @RequestBody ProductRequestDTO dto) {
        Product product = new Product();
        product.setId(id);
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setDescription(dto.getDescription());
        product.setStock(dto.getStock());
        product.setCategory(dto.getCategory());
        product.setImageUrl(dto.getImageUrl());

        Product updated = service.save(product);
        return new ProductResponseDTO(updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
