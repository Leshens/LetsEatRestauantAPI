package com.leshen.LetsEatRestaurantAPI.Controller;

import com.leshen.LetsEatRestaurantAPI.Contract.RestaurantDto;
import com.leshen.LetsEatRestaurantAPI.Contract.RestaurantListDto;
import com.leshen.LetsEatRestaurantAPI.Contract.RestaurantPanelDto;
import com.leshen.LetsEatRestaurantAPI.Service.RestaurantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
@RequestMapping("/api/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;
    @PostMapping
    @Operation(summary = "Create a new restaurant")
    @ApiResponse(responseCode = "200", description = "Restaurant created", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RestaurantDto.class)))
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content)
    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
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
    @Operation(summary = "Get all restaurants")
    @ApiResponse(responseCode = "200", description = "Menus found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RestaurantDto.class)))
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content)
    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    public ResponseEntity<List<RestaurantDto>> getAllRestaurants(){
        List<RestaurantDto> restaurants = restaurantService.getAllRestaurants();
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    @Operation(summary = "Get a restaurant by ID")
    @ApiResponse(responseCode = "200", description = "Restaurant found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RestaurantDto.class)))
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content)
    @ApiResponse(responseCode = "404", description = "Restaurant not found", content = @Content)
    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    public ResponseEntity<RestaurantDto> getById(
            @Parameter(description="Identifier of the Restaurant")
            @PathVariable long id) {
        Optional<RestaurantDto> restaurant = restaurantService.getRestaurantById(id);
        return restaurant.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant not found"));
    }

    @GetMapping("/token/{token}")
    @Operation(summary = "Get a restaurant by Token")
    @ApiResponse(responseCode = "200", description = "Restaurant found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RestaurantDto.class)))
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content)
    @ApiResponse(responseCode = "404", description = "Restaurant not found", content = @Content)
    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    public ResponseEntity<RestaurantDto> getByToken(
            @Parameter(description="Authorization token")
            @PathVariable String token) {
        Optional<RestaurantDto> restaurant = restaurantService.getRestaurantByToken(token);
        return restaurant.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant not found"));
    }

    @GetMapping("/search")
    @Operation(summary = "Get restaurants in radius")
    @ApiResponse(responseCode = "200", description = "Restaurants found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RestaurantListDto.class)))
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content)
    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    public ResponseEntity<List<RestaurantListDto>> searchRestaurantsInRadius(
            @Parameter(description="Latitude")
            @RequestParam double latitude,
            @Parameter(description="Longitude")
            @RequestParam double longitude,
            @Parameter(description="Radius in km")
            @RequestParam double radius) {
        System.out.println("Latitude: " + latitude);
        System.out.println("Longitude: " + longitude);
        System.out.println("Radius: " + radius);

        List<RestaurantListDto> restaurants = restaurantService.findRestaurantsInRadius(latitude, longitude, radius);

        if (restaurants.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    @GetMapping("/panel/{id}")
    @Operation(summary = "Get panel info about restaurant by ID")
    @ApiResponse(responseCode = "200", description = "Restaurant found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RestaurantPanelDto.class)))
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content)
    @ApiResponse(responseCode = "404", description = "Restaurant not found", content = @Content)
    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    public ResponseEntity<RestaurantPanelDto> getRestaurant(
            @Parameter(description="Identifier of the Restaurant")
            @PathVariable Long id) {
        RestaurantPanelDto restaurantPanelDto = restaurantService.getRestaurantPanelById(id);

        if (restaurantPanelDto != null) {
            return ResponseEntity.ok(restaurantPanelDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PatchMapping("/{id}")
    @Operation(summary = "Update a restaurant")
    @ApiResponse(responseCode = "200", description = "Restaurant updated", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RestaurantDto.class)))
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content)
    @ApiResponse(responseCode = "401", description = "Unauthorized, bad token", content = @Content)
    @ApiResponse(responseCode = "404", description = "Restaurant not found", content = @Content)
    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    public ResponseEntity<RestaurantDto> patchRestaurant(
            @Parameter(description="Identifier of the Restaurant")
            @PathVariable Long id,
            @RequestBody RestaurantDto restaurantDto,
            @Parameter(description="Authorization token")
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

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a restaurant")
    @ApiResponse(responseCode = "200", description = "Restaurant deleted", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Long.class)))
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content)
    @ApiResponse(responseCode = "401", description = "Unauthorized, bad token", content = @Content)
    @ApiResponse(responseCode = "404", description = "Restaurant not found", content = @Content)
    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    public ResponseEntity<Long> deleteRestaurant(
            @Parameter(description="Identifier of the Restaurant")
            @PathVariable Long id,
            @Parameter(description="Authorization token")
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
}
