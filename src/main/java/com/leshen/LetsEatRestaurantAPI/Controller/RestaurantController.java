package com.leshen.LetsEatRestaurantAPI.Controller;

import com.leshen.LetsEatRestaurantAPI.Contract.RestaurantDto;
import com.leshen.LetsEatRestaurantAPI.Contract.RestaurantListDto;
import com.leshen.LetsEatRestaurantAPI.Contract.RestaurantPanelDto;
import com.leshen.LetsEatRestaurantAPI.Model.Restaurant;
import com.leshen.LetsEatRestaurantAPI.Repository.RestaurantRepository;
import com.leshen.LetsEatRestaurantAPI.Service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000")
@RequiredArgsConstructor
@RequestMapping("/api/restaurants") // Common base path for all restaurant-related endpoints
public class RestaurantController {

    private final RestaurantService restaurantService;
    @PostMapping
    public ResponseEntity<Long> newRestaurant(@RequestBody RestaurantDto newRestaurantDto){
       Long id = restaurantService.createRestaurant(newRestaurantDto);

        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/id/{id}")
                .buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(uri).build();

    }
    @GetMapping
    public ResponseEntity<List<RestaurantDto>> getAllRestaurants(){
        List<RestaurantDto> restaurants = restaurantService.getAllRestaurants();
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }
    @GetMapping("/search")
    public ResponseEntity<List<RestaurantListDto>> searchRestaurantsInRadius(
            @RequestParam double latitude,
            @RequestParam double longitude,
            @RequestParam double radius) {
        System.out.println("Latitude: " + latitude);
        System.out.println("Longitude: " + longitude);
        System.out.println("Radius: " + radius);

        List<RestaurantListDto> restaurants = restaurantService.findRestaurantsInRadius(latitude, longitude, radius);

        if (restaurants.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(restaurants, HttpStatus.FOUND);
    }
    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDto> getById(@PathVariable long id) {
        Optional<RestaurantDto> restaurant = restaurantService.getRestaurantById(id);
        return restaurant.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant not found"));
    }
    @GetMapping("/panel/{id}")
    public ResponseEntity<RestaurantPanelDto> getRestaurant(@PathVariable Long id) {
        RestaurantPanelDto restaurantPanelDto = restaurantService.getRestaurantPanelById(id);

        if (restaurantPanelDto != null) {
            return ResponseEntity.ok(restaurantPanelDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/token/{token}")
    public ResponseEntity<RestaurantDto> getByToken(@PathVariable String token) {
        Optional<RestaurantDto> restaurant = restaurantService.getRestaurantByToken(token);
        return restaurant.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant not found"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteRestaurant(@PathVariable Long id,
                                                 @RequestHeader("Authorization") String requestToken) {
        try {
            if (!restaurantService.verifyToken(id, requestToken)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            restaurantService.deleteRestaurant(id);
            return new ResponseEntity<>(id, HttpStatus.OK);

        } catch (RuntimeException e) {
            if (e instanceof NoSuchElementException) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

    }

    @PatchMapping("/{id}")
    public ResponseEntity<RestaurantDto> patchRestaurant(
            @PathVariable Long id,
            @RequestBody RestaurantDto restaurantDto,
            @RequestHeader("Authorization") String requestToken) {

        try {
            if (!restaurantService.verifyToken(id, requestToken)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            RestaurantDto patchedRestaurant = restaurantService.patchRestaurant(id, restaurantDto);
            return ResponseEntity.ok(patchedRestaurant);

        } catch (RuntimeException e) {
            if (e instanceof NoSuchElementException) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
}
