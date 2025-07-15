package com.agrocart.web.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WishlistDTO {
    private String productId;
    private String addedAt;
}
