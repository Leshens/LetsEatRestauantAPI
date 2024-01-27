package com.leshen.LetsEatRestaurantAPI.Service;

import com.leshen.LetsEatRestaurantAPI.Contract.ReviewDto;
import com.leshen.LetsEatRestaurantAPI.Model.Restaurant;
import com.leshen.LetsEatRestaurantAPI.Model.Review;
import com.leshen.LetsEatRestaurantAPI.Repository.ReviewRepository;
import com.leshen.LetsEatRestaurantAPI.Service.Mappers.ReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper = ReviewMapper.INSTANCE;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository){
        this.reviewRepository = reviewRepository;}

    public ReviewDto createReview(ReviewDto reviewDto) {

        if (isReviewWithinHalfYear(reviewDto)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Another review with the same token exists within half a year.");
        }

        Review review = reviewMapper.toEntity(reviewDto);
        return reviewMapper.toDto(reviewRepository.save(review));
    }

    public List<ReviewDto> getReviewsForRestaurant(Long restaurantId) {
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantId(restaurantId);

        List<Review> reviews = reviewRepository.findByRestaurant(restaurant);
        return reviews.stream()
                .map(reviewMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<ReviewDto> getAllReviews() {
        return reviewRepository.findAll().stream()
                .map(reviewMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<ReviewDto> getReviewById(long id) {
        return reviewRepository.findById(id).map(reviewMapper::toDto);
    }

    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }

    public ReviewDto patchReview(long id, ReviewDto reviewDto) {
        Review existingReview = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));
        Review patchedReview = reviewMapper.patchReviewFromDto(reviewDto, existingReview);
        return reviewMapper.toDto(reviewRepository.save(patchedReview));
    }

    private boolean isReviewWithinHalfYear(ReviewDto reviewDto) {
        LocalDate halfYearAgo = LocalDate.now().minusMonths(6);
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantId(reviewDto.getRestaurantId());

        List<Review> reviewsForRestaurantWithinHalfYear = reviewRepository.findByRestaurantAndDateAfter(
                restaurant, halfYearAgo);

        return reviewsForRestaurantWithinHalfYear.stream()
                .anyMatch(review -> review.getToken().equals(reviewDto.getToken()));
    }

    public boolean verifyToken(Long reviewId, String requestToken) {
        try {
            Review review = reviewRepository.findById(reviewId).get();
            return review.getToken().equals(requestToken);
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
