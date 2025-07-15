package com.agrocart.web.service;

import com.agrocart.web.dto.WishlistDTO;
import com.agrocart.web.model.WishlistItem;
import com.agrocart.web.repository.WishlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WishlistService {

    private final WishlistRepository wishlistRepository;

    public List<WishlistDTO> getWishlist(String userId) {
        return wishlistRepository.findByUserId(userId).stream()
                .map(item -> WishlistDTO.builder()
                        .productId(item.getProductId())
                        .addedAt(item.getAddedAt())
                        .build())
                .collect(Collectors.toList());
    }

    public void addToWishlist(String userId, String productId) {
        boolean alreadyExists = wishlistRepository.existsByUserIdAndProductId(userId, productId);
        if (!alreadyExists) {
            WishlistItem item = WishlistItem.builder()
                    .userId(userId)
                    .productId(productId)
                    .addedAt(Instant.now().toString())
                    .build();
            wishlistRepository.save(item);
        }
    }

    public void removeFromWishlist(String userId, String productId) {
        wishlistRepository.deleteByUserIdAndProductId(userId, productId);
    }
}
