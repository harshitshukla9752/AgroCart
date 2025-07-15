package com.agrocart.web.controller;

import com.agrocart.web.dto.WishlistDTO;
import com.agrocart.web.service.WishlistService;
import com.agrocart.web.config.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class WishlistController {

    private final WishlistService wishlistService;
    private final JwtUtil jwtUtil;

    @GetMapping("/wishlist")
    public ResponseEntity<List<WishlistDTO>> getWishlist(@RequestHeader("Authorization") String token) {
        String userId = jwtUtil.extractUserId(token.substring(7));
        return ResponseEntity.ok(wishlistService.getWishlist(userId));
    }

    @PostMapping("/wishlist/add")
    public ResponseEntity<Void> addToWishlist(@RequestHeader("Authorization") String token,
                                              @RequestParam String productId) {
        String userId = jwtUtil.extractUserId(token.substring(7));
        wishlistService.addToWishlist(userId, productId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/wishlist/remove")
    public ResponseEntity<Void> removeFromWishlist(@RequestHeader("Authorization") String token,
                                                   @RequestParam String productId) {
        String userId = jwtUtil.extractUserId(token.substring(7));
        wishlistService.removeFromWishlist(userId, productId);
        return ResponseEntity.ok().build();
    }
}
