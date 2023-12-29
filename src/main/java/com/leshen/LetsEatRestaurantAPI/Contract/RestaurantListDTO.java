package com.leshen.LetsEatRestaurantAPI.Contract;

import com.leshen.LetsEatRestaurantAPI.Model.RestaurantCategory;
import com.leshen.LetsEatRestaurantAPI.Model.Tables;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantListDTO {
    private Long restaurantId;
    private String restaurantName;
    private RestaurantCategory restaurantCategory;
    private String openingHours;
    private String photoLink;
    private Short stars;
    private Double longitude;
    private Double latitude;
    private List<Tables> tables;
}
