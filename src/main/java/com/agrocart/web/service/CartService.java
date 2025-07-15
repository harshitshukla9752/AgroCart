package com.agrocart.web.service;

import com.agrocart.web.model.Cart;
import com.agrocart.web.dto.CartResponse;
import com.agrocart.web.dto.ProductDTO;
import com.agrocart.web.repository.CartRepository;
import com.agrocart.web.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

public interface CartService {
    List<CartResponse> getCart(String userId);
    Cart addItem(String userId, Cart item);
    Cart updateQuantity(String userId, String productId, int quantity);
    void removeItem(String userId, String productId);
}

@Service
@RequiredArgsConstructor
class CartServiceImpl implements CartService {

    private final CartRepository cartRepo;
    private final ProductRepository productRepo;

    @Override
    public List<CartResponse> getCart(String userId) {
        List<Cart> items = cartRepo.findByUserId(userId);
        List<CartResponse> response = new ArrayList<>();

        for (Cart item : items) {
            productRepo.findById(item.getProductId()).ifPresent(product -> {
                response.add(CartResponse.builder()
                        .productId(item.getProductId())
                        .quantity(item.getQuantity())
                        .product(ProductDTO.builder()
                                .id(product.getId())
                                .name(product.getName())
                                .image(product.getImageUrl())
                                .price(product.getPrice())
                                .build())
                        .build());
            });
        }

        return response;
    }

    @Override
    public Cart addItem(String userId, Cart item) {
        Optional<Cart> existing = cartRepo.findByUserIdAndProductId(userId, item.getProductId());
        if (existing.isPresent()) {
            Cart existingItem = existing.get();
            existingItem.setQuantity(existingItem.getQuantity() + item.getQuantity());
            return cartRepo.save(existingItem);
        }
        item.setUserId(userId);
        return cartRepo.save(item);
    }

    @Override
    public Cart updateQuantity(String userId, String productId, int quantity) {
        Cart item = cartRepo.findByUserIdAndProductId(userId, productId)
                .orElseThrow(() -> new RuntimeException("Item not found"));
        item.setQuantity(quantity);
        return cartRepo.save(item);
    }

    @Override
    public void removeItem(String userId, String productId) {
        cartRepo.deleteByUserIdAndProductId(userId, productId);
    }
}
