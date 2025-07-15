package com.agrocart.web.controller;

import com.agrocart.web.dto.ReviewRequestDTO;
import com.agrocart.web.dto.ReviewResponseDTO;
import com.agrocart.web.model.Review;
import com.agrocart.web.service.ReviewService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public ReviewResponseDTO addReview(@RequestBody ReviewRequestDTO dto) {
        Review review = new Review();
        review.setProductId(dto.getProductId());
        review.setUserId(dto.getUserId());
        review.setRating(dto.getRating());
        review.setComment(dto.getComment());
        return new ReviewResponseDTO(reviewService.save(review));
    }

    @GetMapping("/product/{productId}")
    public List<ReviewResponseDTO> getByProduct(@PathVariable String productId) {
        return reviewService.getByProduct(productId).stream()
                .map(ReviewResponseDTO::new)
                .toList(); // or use Collectors.toList() for Java < 16
    }
}

