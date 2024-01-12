package com.leshen.LetsEatRestaurantAPI.Contract;

import com.leshen.LetsEatRestaurantAPI.Model.Menu;
import com.leshen.LetsEatRestaurantAPI.Model.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
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
