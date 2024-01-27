package com.leshen.LetsEatRestaurantAPI.Contract;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {

    @Schema(description = "Unique identifier for the review")
    private Long reviewId;

    @Schema(description = "Token for authorization purposes", defaultValue = "your_token", example = "your_token")
    private String token;

    @Schema(description = "Review description", defaultValue = "Very nice", example = "Good food")
    private String comment;

    @Schema(description = "Date of the review")
    private LocalDate date;

    @Schema(description = "Identifier for the restaurant to which the review belongs")
    private Long restaurantId;

    @Schema(description = "Service rating", minimum = "1", maximum = "5", example = "5", defaultValue = "5")
    private int service;

    @Schema(description = "Food rating", minimum = "1", maximum = "5", example = "5", defaultValue = "5")
    private int food;

    @Schema(description = "Atmosphere rating", minimum = "1", maximum = "5", example = "5", defaultValue = "5")
    private int atmosphere;
}
