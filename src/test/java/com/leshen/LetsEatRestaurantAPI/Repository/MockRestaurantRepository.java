package com.leshen.LetsEatRestaurantAPI.Repository;

import com.leshen.LetsEatRestaurantAPI.Model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MockRestaurantRepository {

    private final List<Restaurant> restaurants = new ArrayList<>();

    public Restaurant save(Restaurant restaurant) {
        restaurants.add(restaurant);
        return restaurant;
    }

    public List<Restaurant> findAll() {
        return new ArrayList<>(restaurants);
    }

    public Optional<Restaurant> findById(Long id) {
        return restaurants.stream().filter(r -> r.getRestaurantId().equals(id)).findFirst();
    }
}

