package com.leshen.LetsEatRestaurantAPI.Controller;

import com.leshen.LetsEatRestaurantAPI.Contract.ReviewDto;
import com.leshen.LetsEatRestaurantAPI.Service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@CrossOrigin("http://localhost:3000")
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    @Operation(summary = "Create a new review")
    @ApiResponse(responseCode = "200", description = "Review created", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ReviewDto.class)))
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content)
    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    public ResponseEntity<ReviewDto> createReview(@RequestBody ReviewDto reviewDto) {
        ReviewDto createdReview = reviewService.createReview(reviewDto);
        return new ResponseEntity<>(createdReview, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Get all reviews")
    @ApiResponse(responseCode = "200", description = "Reviews found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ReviewDto.class)))
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content)
    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    public ResponseEntity<List<ReviewDto>> getAllReviews() {
        List<ReviewDto> reviews = reviewService.getAllReviews();
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a review by ID")
    @ApiResponse(responseCode = "200", description = "Review found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ReviewDto.class)))
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content)
    @ApiResponse(responseCode = "404", description = "Review not found", content = @Content)
    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    public ResponseEntity<ReviewDto> getById(
            @Parameter(description="Identifier of the review")
            @PathVariable long id) {
        ReviewDto review = reviewService.getReviewById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Review not found"));
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{restaurantId}")
    @Operation(summary = "Get reviews for a specific restaurant")
    @ApiResponse(responseCode = "200", description = "Reviews found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ReviewDto.class)))
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content)
    @ApiResponse(responseCode = "404", description = "Reviews not found", content = @Content)
    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    public ResponseEntity<List<ReviewDto>> getReviewsForRestaurant(
            @Parameter(description="Identifier of the restaurant")
            @PathVariable Long restaurantId) {
        List<ReviewDto> reviews = reviewService.getReviewsForRestaurant(restaurantId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update a review")
    @ApiResponse(responseCode = "200", description = "Review updated", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ReviewDto.class)))
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content)
    @ApiResponse(responseCode = "401", description = "Unauthorized, bad token", content = @Content)
    @ApiResponse(responseCode = "404", description = "Review not found", content = @Content)
    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    public ResponseEntity<ReviewDto> patchReview(
            @Parameter(description="Identifier of the review")
            @PathVariable Long id,
            @RequestBody ReviewDto reviewDto,
            @Parameter(description="Authorization token")
            @RequestHeader("Authorization") String requestToken) {

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

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a review")
    @ApiResponse(responseCode = "200", description = "Review deleted", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Long.class)))
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content)
    @ApiResponse(responseCode = "401", description = "Unauthorized, bad token", content = @Content)
    @ApiResponse(responseCode = "404", description = "Review not found", content = @Content)
    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    public ResponseEntity<Long> deleteReview(
            @Parameter(description="Identifier of the review")
            @PathVariable Long id,
            @Parameter(description="Authorization token")
            @RequestHeader("Authorization") String requestToken) {
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
}
