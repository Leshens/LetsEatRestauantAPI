package com.leshen.LetsEatRestaurantAPI.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public List<Restaurant> getRestaurants() {
      return restaurantRepository.findAll();
    }

    @Transactional
    public void updateRestaurant(Integer restaurantId, String restaurantName, String location) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new IllegalStateException(
                        "restaurant with id" + restaurantId + "does not exists"));
        if (restaurantName != null &&
                !restaurantName.isEmpty() &&
                !Objects.equals(restaurant.getRestaurantName(),restaurantName)) {
            restaurant.setRestaurantName(restaurantName);
        }

        if (location != null &&
                !location.isEmpty() &&
                !Objects.equals(restaurant.getLocation(),location)) {
            restaurant.setLocation(location);
        }
    }
}
