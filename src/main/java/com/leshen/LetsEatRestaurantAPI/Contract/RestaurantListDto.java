package com.leshen.LetsEatRestaurantAPI.Contract;

import com.leshen.LetsEatRestaurantAPI.Model.RestaurantCategory;
import lombok.*;

import java.util.List;

@Data
public class RestaurantListDto {
    private Long restaurantId;
    private String restaurantName;
    private RestaurantCategory restaurantCategory;
    private String openingHours;
    private String photoLink;
    private Short stars;
    private Double longitude;
    private Double latitude;
    private List<TableDto> tables;
}