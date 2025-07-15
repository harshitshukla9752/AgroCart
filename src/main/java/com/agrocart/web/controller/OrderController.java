package com.agrocart.web.controller;

import com.agrocart.web.dto.OrderRequestDTO;
import com.agrocart.web.model.Order;
import com.agrocart.web.service.OrderService;
import com.agrocart.web.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user/orders")
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;

    public OrderController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    // ✅ Place order
    @PostMapping
    public ResponseEntity<Order> placeOrder(@RequestBody OrderRequestDTO dto) {
        if (dto.getItems() == null || dto.getItems().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Order order = new Order();
        order.setItems(dto.getItems());
        order.setAddress(dto.getAddress());
        order.setTotalAmount(dto.getTotalAmount());
        order.setOrderDate(new Date());
        order.setStatus("PENDING");
        order.setUserId(userService.getCurrentUser().getId());

        return ResponseEntity.ok(orderService.place(order));
    }

    // ✅ View all user orders
    @GetMapping
    public ResponseEntity<List<Order>> getUserOrders() {
        String userId = userService.getCurrentUser().getId();
        return ResponseEntity.ok(orderService.getByUser(userId));
    }

    // ✅ View specific order (ensure ownership check in service)
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable String id) {
        return orderService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // ✅ Cancel user's own order
    @DeleteMapping("/{id}")
    public ResponseEntity<String> cancelOrder(@PathVariable String id) {
        boolean success = orderService.cancelOrder(id, userService.getCurrentUser().getId());
        if (success) {
            return ResponseEntity.ok("Order canceled");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You can't cancel this order");
        }
    }
}
