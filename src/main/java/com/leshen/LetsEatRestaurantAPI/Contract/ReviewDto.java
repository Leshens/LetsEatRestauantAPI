package com.leshen.LetsEatRestaurantAPI.Contract;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
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

    @Schema(description = "Identifier for the restaurant to which the menu belongs")
    private Long restaurantId;

    @Schema(description = "Service rating", minimum = "1", maximum = "5", example = "4", defaultValue = "4")
    private int service;

    @Schema(description = "Food rating", minimum = "1", maximum = "5", example = "4", defaultValue = "5")
    private int food;

    @Schema(description = "Atmosphere rating", minimum = "1", maximum = "5", example = "4", defaultValue = "4")
    private int atmosphere;
}
