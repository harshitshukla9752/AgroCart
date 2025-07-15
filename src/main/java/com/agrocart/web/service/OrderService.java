package com.agrocart.web.service;

import com.agrocart.web.exception.ResourceNotFoundException;
import com.agrocart.web.model.Order;
import com.agrocart.web.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository repo;

    public OrderService(OrderRepository repo) {
        this.repo = repo;
    }

    public Order place(Order order) {
        order.setOrderDate(new Date());
        order.setStatus("PENDING");
        return repo.save(order);
    }

    public List<Order> getByUser(String userId) {
        return repo.findByUserId(userId);
    }

    public List<Order> getAllOrders() {
        return repo.findAll();
    }

    public Optional<Order> getById(String id) {
        return repo.findById(id);
    }

    public boolean cancelOrder(String orderId, String userId) {
    Optional<Order> orderOpt = repo.findById(orderId);

    if (orderOpt.isEmpty()) {
        return false;
    }

    Order order = orderOpt.get();

    if (!order.getUserId().equals(userId)) {
        return false; // Not allowed to cancel someone else's order
    }

    repo.deleteById(orderId);
    return true;
}

    public Order updateOrderStatus(String id, String status) {
        Order order = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
        order.setStatus(status);
        return repo.save(order);
    }
}
