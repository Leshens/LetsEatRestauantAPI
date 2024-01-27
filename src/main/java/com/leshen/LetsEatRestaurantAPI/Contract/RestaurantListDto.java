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
    private Double longitude;
    private Double latitude;
    private String distance;
    private List<TableDto> tables;
    @Setter
    @Getter
    private double stars;
}