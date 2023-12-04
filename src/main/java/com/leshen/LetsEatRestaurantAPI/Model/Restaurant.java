package com.leshen.LetsEatRestaurantAPI.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {
    @Id
    @GeneratedValue
    private Long restaurantId;
    private Long token;
    private String restaurantName;
    private String location;

    @Enumerated(EnumType.STRING)
    @Column(name = "restaurant_category", length = 32)
    private RestaurantCategory restaurantCategory;

}
