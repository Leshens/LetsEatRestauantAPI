package com.leshen.LetsEatRestaurantAPI.Service;

import com.leshen.LetsEatRestaurantAPI.Contract.RestaurantDto;
import com.leshen.LetsEatRestaurantAPI.Contract.RestaurantListDto;
import com.leshen.LetsEatRestaurantAPI.Contract.RestaurantPanelDto;
import com.leshen.LetsEatRestaurantAPI.Model.Menu;
import com.leshen.LetsEatRestaurantAPI.Model.Restaurant;
import com.leshen.LetsEatRestaurantAPI.Model.Review;
import com.leshen.LetsEatRestaurantAPI.Repository.MenuRepository;
import com.leshen.LetsEatRestaurantAPI.Repository.RestaurantRepository;
import com.leshen.LetsEatRestaurantAPI.Repository.ReviewRepository;
import com.leshen.LetsEatRestaurantAPI.Service.Mappers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final ReviewRepository reviewRepository;
    private final MenuRepository menuRepository;
    private final RestaurantMapper restaurantMapper = RestaurantMapper.INSTANCE;
    private final ReviewMapper reviewMapper = ReviewMapper.INSTANCE;
    private final MenuMapper menuMapper = MenuMapper.INSTANCE;
    private final RestaurantListMapper restaurantListMapper = RestaurantListMapper.INSTANCE;
    private final RestaurantPanelMapper restaurantPanelMapper = RestaurantPanelMapper.INSTANCE;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository, ReviewRepository reviewRepository, MenuRepository menuRepository) {
        this.restaurantRepository = restaurantRepository;
        this.reviewRepository = reviewRepository;
        this.menuRepository = menuRepository;
    }
    public List<RestaurantListDto> findRestaurantsInRadius(double latitude, double longitude, double radius) {
        List<Restaurant> restaurants = restaurantRepository.findRestaurantsInRadius(latitude, longitude, radius);
        return restaurantListMapper.toDtoList(restaurants);
    }
    public Long createRestaurant(RestaurantDto restaurantDto) {
        Restaurant newRestaurant = restaurantMapper.toEntity(restaurantDto);
        return restaurantRepository.save(newRestaurant).getRestaurantId();
    }

    public List<RestaurantDto> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return restaurants.stream().map(restaurantMapper::toDto).collect(Collectors.toList());
    }

    public List<RestaurantDto> getRestaurantsInArea(Double minLatitude, Double maxLatitude,
                                                        Double minLongitude, Double maxLongitude) {
        List<Restaurant> restaurants = restaurantRepository.findRestaurantsInArea(
                minLatitude, maxLatitude, minLongitude, maxLongitude);

        return restaurantMapper.toDtoList(restaurants); //error caused by RestaurantMapper?
    }

    public Optional<RestaurantDto> getRestaurantById(long id) {
        return restaurantRepository.findById(id).map(restaurantMapper::toDto);
    }

    public Optional<RestaurantDto> getRestaurantByToken(Long token) {
        return restaurantRepository.findByToken(token).map(restaurantMapper::toDto);
    }

    public RestaurantDto updateRestaurant(long id, RestaurantDto restaurantDto, Long requestToken) {
        Restaurant existingRestaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        restaurantMapper.updateRestaurantFromDto(restaurantDto, existingRestaurant);

        return restaurantMapper.toDto(restaurantRepository.save(existingRestaurant));
    }

    public void deleteRestaurant(Long id) {
        if (!restaurantRepository.existsById(id)) {
            throw new RuntimeException("Restaurant not found");
        }
        restaurantRepository.deleteById(id);
    }
    public RestaurantPanelDto getRestaurantPanelById(Long id) {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(id);

        return restaurantOptional.map(restaurantPanelMapper::toDto).orElse(null);
    }
//    public RestaurantPanelDto getRestaurantPanelById(Long id) {
//        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
//        if (restaurant.isPresent()) {
//            RestaurantPanelDto restaurantPanelDto = restaurantMapper.toPanelDto(restaurant.get());
//            List<Menu> menu = menuRepository.findByRestaurant(restaurant.get());
//            List<Review> reviews = reviewRepository.findByRestaurant(restaurant.get());
//            restaurantPanelDto.setMenu(menuMapper.toDtoList(menu));             //something wrong here? menuMapper should be List<MenuDto> toDtoList(List<Menu> menus);
//            restaurantPanelDto.setReviews(reviewMapper.toDtoList(reviews));     //same as up, but it provides error?
//            return restaurantPanelDto;
//        } else {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant not found");
//        }
//    }
}