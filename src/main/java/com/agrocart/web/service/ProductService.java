package com.agrocart.web.service;

import com.agrocart.web.exception.ProductNotFoundException;
import com.agrocart.web.model.Product;
import com.agrocart.web.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public List<Product> getAll() {
        return repo.findAll();
    }

    public Page<Product> getAllPaginated(Pageable pageable) {
        return repo.findAll(pageable);
    }

    public Optional<Product> getById(String id) {
        return repo.findById(id);
    }

    public Product save(Product product) {
        return repo.save(product);
    }

    public Product updateProduct(String id, Product updated) {
        return repo.findById(id).map(existing -> {
            existing.setName(updated.getName());
            existing.setPrice(updated.getPrice());
            existing.setCategory(updated.getCategory());
            existing.setDescription(updated.getDescription());
            existing.setImageUrl(updated.getImageUrl());
            existing.setStock(updated.getStock());
            return repo.save(existing);
        }).orElseThrow(() -> new ProductNotFoundException(id));
    }

    public void delete(String id) {
        if (!repo.existsById(id)) {
            throw new ProductNotFoundException(id);
        }
        repo.deleteById(id);
    }

    public List<Product> findByCategory(String category) {
        return repo.findByCategoryIgnoreCase(category);
    }

    public List<Product> findLowStock(int threshold) {
        return repo.findByStockLessThan(threshold);
    }
}
