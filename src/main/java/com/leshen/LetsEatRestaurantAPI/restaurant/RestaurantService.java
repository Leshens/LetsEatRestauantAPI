package com.leshen.LetsEatRestaurantAPI.restaurant;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {
    public List<Restaurant> getRestaurants() {
        return List.of(
                new Restaurant(
                        1,
                        "1",
                        "Restaurax",
                        "Grunwaldzka 5"
                )
        );
    }
}
