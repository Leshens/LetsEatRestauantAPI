package com.leshen.LetsEatRestaurantAPI.Repository;

import com.leshen.LetsEatRestaurantAPI.Model.Review;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MockReviewRepository {
    private final List<Review> reviews = new ArrayList<>();

    public Review save(Review review) {
        reviews.add(review);
        return review;
    }

    public List<Review> findAll() {
        return new ArrayList<>(reviews);
    }

    public Optional<Review> findById(Long id) {
        return reviews.stream().filter(r -> r.getReviewId().equals(id)).findFirst();
    }
}
