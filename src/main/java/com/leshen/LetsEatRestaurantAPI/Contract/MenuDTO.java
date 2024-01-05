package com.leshen.LetsEatRestaurantAPI.Contract;

import lombok.*;

@Data
public class MenuDTO {

    private Long restaurantId;
    private Long token;
    private String name;
    private Float price;
}
