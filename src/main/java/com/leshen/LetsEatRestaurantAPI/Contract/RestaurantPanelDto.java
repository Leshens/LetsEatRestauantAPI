package com.leshen.LetsEatRestaurantAPI.Contract;

import lombok.Data;

import java.util.List;

@Data
public class RestaurantPanelDto {

    private Long restaurantId;
    private String restaurantName;
    private String location;
    private String openingHours;
    private String photoLink;
    private String phoneNumber;
    private String websiteLink;
    private Double longitude;
    private Double latitude;
    private List<MenuDto> menu;
    private List<ReviewDto> reviews;
}
