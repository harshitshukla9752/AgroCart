package com.agrocart.web.controller;

import com.agrocart.web.dto.CartResponse;
import com.agrocart.web.model.Cart;
import com.agrocart.web.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping
public List<CartResponse> getCart() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String userId = (String) auth.getPrincipal(); // ✅ Now the userId
    return cartService.getCart(userId);
}


    @PostMapping
    public Cart addItem(@AuthenticationPrincipal User user, @RequestBody Cart item) {
        return cartService.addItem(user.getUsername(), item);
    }

    @PutMapping("/{productId}")
    public Cart updateQuantity(@AuthenticationPrincipal User user,
                                @PathVariable String productId,
                                @RequestParam int quantity) {
        return cartService.updateQuantity(user.getUsername(), productId, quantity);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> removeItem(@AuthenticationPrincipal User user,
                                           @PathVariable String productId) {
        cartService.removeItem(user.getUsername(), productId);
        return ResponseEntity.ok().build();
    }
}
