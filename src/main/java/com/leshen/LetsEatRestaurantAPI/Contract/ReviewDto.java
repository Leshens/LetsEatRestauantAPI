package com.leshen.LetsEatRestaurantAPI.Contract;

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
    private Long reviewId;
    private String token;
    private String comment;
    private LocalDate date;
    private Long restaurantId;
    private int service;
    private int food;
    private int atmosphere;
}
