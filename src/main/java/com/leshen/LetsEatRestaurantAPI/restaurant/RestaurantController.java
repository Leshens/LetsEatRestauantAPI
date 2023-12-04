package com.leshen.LetsEatRestaurantAPI.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/restaurant")
public class RestaurantController {
    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping
    public List<Restaurant> getRestaurants() {
        return restaurantService.getRestaurants();
    }

    @PutMapping(path = "{restaurantId}")
    public void updateRestaurant(
            @PathVariable("restaurantId") Integer restaurantId,
            @RequestParam(required = false) String restaurantName,
            @RequestParam(required = false) String location) {
        restaurantService.updateRestaurant(restaurantId, restaurantName , location);
    }
}
