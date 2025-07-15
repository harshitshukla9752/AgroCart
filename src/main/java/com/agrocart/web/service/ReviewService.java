package com.agrocart.web.service;

import com.agrocart.web.model.Review;
import com.agrocart.web.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository repo;

    public ReviewService(ReviewRepository repo) {
        this.repo = repo;
    }

    public Review save(Review review) {
        review.setCreated(new Date());
        return repo.save(review);
    }

    public List<Review> getByProduct(String productId) {
        return repo.findByProductId(productId);
    }
}
