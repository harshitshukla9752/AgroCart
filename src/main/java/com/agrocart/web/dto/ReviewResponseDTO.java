package com.agrocart.web.dto;

import com.agrocart.web.model.Review;
import lombok.Data;

@Data
public class ReviewResponseDTO {
    private String id;
    private String productId;
    private String userId;
    private int rating;
    private String comment;

    public ReviewResponseDTO(Review review) {
        this.id = review.getId();
        this.productId = review.getProductId();
        this.userId = review.getUserId();
        this.rating = review.getRating();
        this.comment = review.getComment();
    }

    // Getters and setters
}
