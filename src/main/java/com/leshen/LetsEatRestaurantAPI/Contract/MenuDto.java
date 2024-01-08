package com.leshen.LetsEatRestaurantAPI.Contract;

import lombok.*;

@Data
public class MenuDto {

    private Long restaurantId;
    private Long token;
    private String name;
    private Float price;
}
