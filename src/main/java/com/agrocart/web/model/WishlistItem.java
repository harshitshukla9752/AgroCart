package com.agrocart.web.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "wishlist")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WishlistItem {
    @Id
    private String id;
    private String userId;
    private String productId;
    private String addedAt;
}
