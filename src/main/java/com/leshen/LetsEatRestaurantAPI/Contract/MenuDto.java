package com.leshen.LetsEatRestaurantAPI.Contract;

import lombok.*;

@Data
public class MenuDto {
    private Long reviewId;
    private Long restaurantId;
    private String token;
    private String name;
    private Float price;
}
