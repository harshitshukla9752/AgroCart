package com.agrocart.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

import com.agrocart.web.repository.*;

@RestController
@RequestMapping("/admin/dashboard")
public class AdminDashboardController {

    private final UserRepository userRepo;
    private final OrderRepository orderRepo;
    private final ProductRepository productRepo;

    public AdminDashboardController(UserRepository userRepo, OrderRepository orderRepo, ProductRepository productRepo) {
        this.userRepo = userRepo;
        this.orderRepo = orderRepo;
        this.productRepo = productRepo;
    }

    @GetMapping("/stats")
    public Map<String, Object> getStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalUsers", userRepo.count());
        stats.put("totalOrders", orderRepo.count());
        stats.put("totalProducts", productRepo.count());
        return stats;
    }
}
