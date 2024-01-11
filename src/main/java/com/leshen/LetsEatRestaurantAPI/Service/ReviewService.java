package com.leshen.LetsEatRestaurantAPI.Service;

import com.leshen.LetsEatRestaurantAPI.Contract.ReviewDto;
import com.leshen.LetsEatRestaurantAPI.Model.Restaurant;
import com.leshen.LetsEatRestaurantAPI.Model.Review;
import com.leshen.LetsEatRestaurantAPI.Repository.ReviewRepository;
import com.leshen.LetsEatRestaurantAPI.Service.Mappers.ReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    public List<ReviewDto> getReviewsForRestaurant(Long restaurantId) {
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantId(restaurantId);

        List<Review> reviews = reviewRepository.findByRestaurant(restaurant);
        return reviews.stream()
                .map(reviewMapper::toDto)
                .collect(Collectors.toList());
    }

    public ReviewDto createReview(ReviewDto reviewDto) {
        Review review = reviewMapper.toEntity(reviewDto);

        Review savedReview = reviewRepository.save(review);
        return reviewMapper.toDto(savedReview);
    }
}
