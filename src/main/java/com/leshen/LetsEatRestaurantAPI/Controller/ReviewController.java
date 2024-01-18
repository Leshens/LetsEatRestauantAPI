package com.leshen.LetsEatRestaurantAPI.Controller;

import com.leshen.LetsEatRestaurantAPI.Contract.RestaurantDto;
import com.leshen.LetsEatRestaurantAPI.Contract.ReviewDto;
import com.leshen.LetsEatRestaurantAPI.Service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000")
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<ReviewDto>> getReviewsForRestaurant(@PathVariable Long restaurantId) {
        List<ReviewDto> reviews = reviewService.getReviewsForRestaurant(restaurantId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ReviewDto> createReview(@RequestBody ReviewDto reviewDto) {
        ReviewDto createdReview = reviewService.createReview(reviewDto);
        return new ResponseEntity<>(createdReview, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ReviewDto>> getAllReviews() {
        List<ReviewDto> reviews = reviewService.getAllReviews();
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewDto> getById(@PathVariable long id) {
        ReviewDto review = reviewService.getReviewById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Review not found"));
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteReview(
            @PathVariable Long id,
            @RequestHeader("Authorization") Long requestToken) {
        try {
            if (!reviewService.verifyToken(id, requestToken)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            reviewService.deleteReview(id);
            return new ResponseEntity<>(id, HttpStatus.OK);

        } catch (RuntimeException e) {
            if (e instanceof NoSuchElementException) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ReviewDto> patchReview(
            @PathVariable Long id,
            @RequestBody ReviewDto reviewDto,
            @RequestHeader("Authorization") Long requestToken) {

        try {
            if (!reviewService.verifyToken(id, requestToken)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            ReviewDto patchedReview = reviewService.patchReview(id, reviewDto);
            return ResponseEntity.ok(patchedReview);

        } catch (RuntimeException e) {
            if (e instanceof NoSuchElementException) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
}
