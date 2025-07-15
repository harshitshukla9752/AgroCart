package com.agrocart.web.service;

import com.agrocart.web.model.Product;
import com.agrocart.web.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final ProductRepository repo;

    public AdminService(ProductRepository repo) {
        this.repo = repo;
    }

    public List<Product> getAll() {
        return repo.findAll();
    }

    public Product add(Product product) {
        return repo.save(product);
    }

    public Product update(Product product) {
        return repo.save(product);
    }

    public void delete(String id) {
        repo.deleteById(id);
    }
}
