package com.agrocart.web.dto;

import com.agrocart.web.model.WishlistItem;
import lombok.Data;

@Data
public class WishlistResponseDTO {
    private String id;
    private String userId;
    private String productId;

    public WishlistResponseDTO(WishlistItem item) {
        this.id = item.getId();
        this.userId = item.getUserId();
        this.productId = item.getProductId();
    }
}
