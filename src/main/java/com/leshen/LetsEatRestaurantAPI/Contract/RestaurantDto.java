package com.leshen.LetsEatRestaurantAPI.Contract;

import com.leshen.LetsEatRestaurantAPI.Model.RestaurantCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDto {

    private String token;
    private String restaurantName;
    private String location;
    private RestaurantCategory restaurantCategory;
    private String openingHours;
    private String photoLink;
    private String websiteLink;
    private Double longitude;
    private Double latitude;
    private String phoneNumber;
}
