package com.leshen.LetsEatRestaurantAPI.Controller;

import com.leshen.LetsEatRestaurantAPI.Contract.RestaurantDto;
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
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final RestaurantRepository restaurantRepository;


    @PostMapping("/restaurant")
    public ResponseEntity<Long> newRestaurant(@RequestBody RestaurantDto newRestaurantDto){
       Long id = restaurantService.createRestaurant(newRestaurantDto);

        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/id/{id}")
                .buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(uri).build();

    }
    @GetMapping("/restaurants")
    public ResponseEntity<List<RestaurantDto>> getAllRestaurants(){
        List<RestaurantDto> restaurants = restaurantService.getAllRestaurants();
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }
    @GetMapping("/restaurant/id/{id}")
    public ResponseEntity<RestaurantDto> getById(@PathVariable long id) {
        Optional<RestaurantDto> restaurant = restaurantService.getRestaurantById(id);
        return restaurant.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant not found"));
    }

    @GetMapping("/restaurant/{id}/panel")
    public ResponseEntity<RestaurantPanelDto> getRestaurantPanelById(@PathVariable long id) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        if (restaurant.isPresent()) {
            RestaurantPanelDto restaurantPanelDto = restaurantService.getRestaurantPanelById(id);
            return new ResponseEntity<>(restaurantPanelDto, HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant not found");
        }
    }

    @GetMapping("/restaurant/token/{token}")
    public ResponseEntity<RestaurantDto> getByToken(@PathVariable Long token) {
        Optional<RestaurantDto> restaurant = restaurantService.getRestaurantByToken(token);
        return restaurant.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant not found"));
    }


    @PutMapping("updateRestaurant/{id}")
    public ResponseEntity<RestaurantDto> updateRestaurant(@PathVariable long id,@RequestBody RestaurantDto updatedRestaurantDto,
                                                       @RequestHeader("Authorization") Long requestToken) {
        RestaurantDto updatedDto = restaurantService.updateRestaurant(id, updatedRestaurantDto, requestToken);
        return ResponseEntity.ok(updatedDto);
    }
    @DeleteMapping(value = "/deleteRestaurant/{id}")
    public ResponseEntity<Long> deleteRestaurant(@PathVariable Long id,
                                                 @RequestHeader("Authorization") Long requestToken) {
       restaurantService.deleteRestaurant(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
