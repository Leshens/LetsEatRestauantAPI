package com.leshen.LetsEatRestaurantAPI.Controller;

import com.leshen.LetsEatRestaurantAPI.Model.Restaurant;
import com.leshen.LetsEatRestaurantAPI.Repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000")
public class RestaurantController {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @PostMapping("/restaurant")
    Restaurant newRestaurant(@RequestBody Restaurant newRestaurant){
        return restaurantRepository.save(newRestaurant);
    }
    @GetMapping("/restaurants")
    public ResponseEntity<List<Restaurant>> getAllRestaurants(){
        return new ResponseEntity<>(restaurantRepository.findAll(), HttpStatus.OK);
    }
    @GetMapping("/restaurant/{id}")
    public ResponseEntity<Restaurant> getById(@PathVariable long id) {

        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        if (restaurant.isPresent()) {
            return new ResponseEntity<>(restaurant.get(), HttpStatus.OK);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Restaurant not found"
            );
        }
    }
    @PutMapping("updateRestaurant/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable long id,@RequestBody Restaurant restaurant) {
        Restaurant updateRestaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Restaurant not found"
                ));

        updateRestaurant.setRestaurantName(restaurant.getRestaurantName());
        updateRestaurant.setLocation(restaurant.getLocation());
        updateRestaurant.setRestaurantCategory(restaurant.getRestaurantCategory());

        restaurantRepository.save(updateRestaurant);

        return ResponseEntity.ok(updateRestaurant);
    }
    @DeleteMapping(value = "/deleteRestaurant/{id}")
    public ResponseEntity<Long> deleteRestaurant(@PathVariable Long id) {

        if (!restaurantRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Restaurant not found"
            );
        }
        restaurantRepository.deleteById(id);
        return new ResponseEntity<>(id, HttpStatus.OK);

    }
}
