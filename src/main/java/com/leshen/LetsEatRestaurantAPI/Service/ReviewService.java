package com.leshen.LetsEatRestaurantAPI.Service;

import com.leshen.LetsEatRestaurantAPI.Contract.ReviewDto;
import com.leshen.LetsEatRestaurantAPI.Model.Restaurant;
import com.leshen.LetsEatRestaurantAPI.Model.Review;
import com.leshen.LetsEatRestaurantAPI.Repository.RestaurantRepository;
import com.leshen.LetsEatRestaurantAPI.Repository.ReviewRepository;
import com.leshen.LetsEatRestaurantAPI.Service.Mappers.RestaurantMapper;
import com.leshen.LetsEatRestaurantAPI.Service.Mappers.ReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final RestaurantRepository restaurantRepository;
    private final ReviewMapper reviewMapper = ReviewMapper.INSTANCE;
    private final RestaurantMapper restaurantMapper = RestaurantMapper.INSTANCE;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, RestaurantRepository restaurantRepository){
        this.reviewRepository = reviewRepository;
        this.restaurantRepository = restaurantRepository;}

    public List<ReviewDto> getReviewsForRestaurant(Long restaurantId) {
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantId(restaurantId);

        List<Review> reviews = reviewRepository.findByRestaurant(restaurant);
        return reviews.stream()
                .map(reviewMapper::toDto)
                .collect(Collectors.toList());
    }

    public ReviewDto createReview(ReviewDto reviewDto) {

        if (isReviewWithinHalfYear(reviewDto)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Another review with the same token exists within half a year.");
        }

        Review review = reviewMapper.toEntity(reviewDto);
        return reviewMapper.toDto(reviewRepository.save(review));
    }

//    public void updateReview(Long id, ReviewDto reviewDto) {
//        Review existingReview = reviewRepository.findById(id)
//                .orElseThrow(() -> new ResponseStatusException(
//                        HttpStatus.NOT_FOUND, "Review not found"
//                ));
//        existingReview.setService(reviewDto.getService());
//        existingReview.setFood(reviewDto.getFood());
//        existingReview.setAtmosphere(reviewDto.getAtmosphere());
//        existingReview.setComment(reviewDto.getComment());
//
//        reviewRepository.save(existingReview);
//    }

    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }

    private boolean isReviewWithinHalfYear(ReviewDto reviewDto) {
        LocalDateTime halfYearAgo = LocalDateTime.now().minusMonths(6);
        List<Review> reviewsWithSameToken = reviewRepository.findByTokenAndDateAfter(
                reviewDto.getToken(), halfYearAgo);

        return !reviewsWithSameToken.isEmpty();
    }

    public List<ReviewDto> getAllReviews() {
        return reviewRepository.findAll().stream()
                .map(reviewMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<ReviewDto> getReviewById(long id) {
        return reviewRepository.findById(id).map(reviewMapper::toDto);
    }

    public ReviewDto patchReview(long id, ReviewDto reviewDto) {
        Review existingReview = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));
        Review patchedReview = reviewMapper.patchReviewFromDto(reviewDto, existingReview);
        return reviewMapper.toDto(reviewRepository.save(patchedReview));
    }

    public boolean verifyToken(Long reviewId, Long requestToken) {
        try {
            Review review = reviewRepository.findById(reviewId).get();
            return review.getToken().equals(requestToken);
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
