package com.leshen.LetsEatRestaurantAPI.Service;

import com.leshen.LetsEatRestaurantAPI.Contract.RestaurantDto;
import com.leshen.LetsEatRestaurantAPI.Contract.RestaurantListDto;
import com.leshen.LetsEatRestaurantAPI.Contract.RestaurantPanelDto;
import com.leshen.LetsEatRestaurantAPI.Contract.TableDto;
import com.leshen.LetsEatRestaurantAPI.Model.Menu;
import com.leshen.LetsEatRestaurantAPI.Model.Restaurant;
import com.leshen.LetsEatRestaurantAPI.Model.Review;
import com.leshen.LetsEatRestaurantAPI.Repository.MenuRepository;
import com.leshen.LetsEatRestaurantAPI.Repository.RestaurantRepository;
import com.leshen.LetsEatRestaurantAPI.Repository.ReviewRepository;
import com.leshen.LetsEatRestaurantAPI.Repository.TablesRepository;
import com.leshen.LetsEatRestaurantAPI.Service.Mappers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final TablesRepository tablesRepository;
    private final ReviewRepository reviewRepository;
    private final MenuRepository menuRepository;
    private final RestaurantMapper restaurantMapper = RestaurantMapper.INSTANCE;
    private final ReviewMapper reviewMapper = ReviewMapper.INSTANCE;
    private final MenuMapper menuMapper = MenuMapper.INSTANCE;
    private final RestaurantListMapper restaurantListMapper = RestaurantListMapper.INSTANCE;
    private final RestaurantPanelMapper restaurantPanelMapper = RestaurantPanelMapper.INSTANCE;

    private final TableMapper tablesMapper = TableMapper.INSTANCE;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository, ReviewRepository reviewRepository, MenuRepository menuRepository, TablesRepository tablesRepository) {
        this.restaurantRepository = restaurantRepository;
        this.reviewRepository = reviewRepository;
        this.menuRepository = menuRepository;
        this.tablesRepository = tablesRepository;
    }

    public List<RestaurantListDto> findRestaurantsInRadius(double latitude, double longitude, double radius) {
        List<Restaurant> restaurants = restaurantRepository.findRestaurantsInRadius(latitude, longitude, radius);
        List<RestaurantListDto> restaurantListDtos = restaurantListMapper.toDtoList(restaurants);

        for (int i = 0; i < restaurants.size(); i++) {
            Restaurant restaurant = restaurants.get(i);

            double distance = calculateDistance(latitude, longitude, restaurant.getLatitude(), restaurant.getLongitude());
            String formattedDistance = String.format("%.2f km", distance);
            restaurantListDtos
                    .get(i)
                    .setDistance(formattedDistance);

            // Fetch and set tables
            List<TableDto> tables = tablesRepository
                    .findByRestaurant(restaurant)
                    .stream()
                    .map(tablesMapper::toDto)
                    .collect(Collectors.toList());
            restaurantListDtos.get(i).setTables(tables);
        }

        return restaurantListDtos;
    }

    private double calculateDistance(double userLatitude, double userLongitude, double restaurantLatitude, double restaurantLongitude) {
        //Haversine formula
        double earthRadius = 6371;
        double dLat = Math.toRadians(restaurantLatitude - userLatitude);
        double dLon = Math.toRadians(restaurantLongitude - userLongitude);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(Math.toRadians(userLatitude)) * Math.cos(Math.toRadians(restaurantLatitude)) * Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return earthRadius * c;
    }

    public Long createRestaurant(RestaurantDto restaurantDto) {
        Restaurant newRestaurant = restaurantMapper.toEntity(restaurantDto);
        return restaurantRepository.save(newRestaurant).getRestaurantId();
    }

    public List<RestaurantDto> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return restaurants.stream().map(restaurantMapper::toDto).collect(Collectors.toList());
    }

    public Optional<RestaurantDto> getRestaurantById(long id) {
        return restaurantRepository.findById(id).map(restaurantMapper::toDto);
    }

    public Optional<RestaurantDto> getRestaurantByToken(String token) {
        return restaurantRepository.findByToken(token).map(restaurantMapper::toDto);
    }

    public RestaurantDto patchRestaurant(long id, RestaurantDto restaurantDto) {
        Restaurant existingRestaurant = restaurantRepository.findById(id).orElseThrow(() -> new RuntimeException("Restaurant not found"));

        Restaurant patchedRestaurant = restaurantMapper.patchRestaurantFromDto(restaurantDto, existingRestaurant);

        return restaurantMapper.toDto(restaurantRepository.save(patchedRestaurant));
    }

    public void deleteRestaurant(Long id) {
        if (!restaurantRepository.existsById(id)) {
            throw new RuntimeException("Restaurant not found");
        }
        restaurantRepository.deleteById(id);
    }

    public RestaurantPanelDto getRestaurantPanelById(Long id) {
        return restaurantRepository.findById(id).map(restaurantPanelMapper::toDto).map(restaurantPanelDto -> {
            List<Review> reviews = reviewRepository.findByRestaurant(restaurantRepository.findById(id).get());
            restaurantPanelDto.setReviews(reviews.stream().map(reviewMapper::toDto).collect(Collectors.toList()));

            double averageService = calculateAverageRating(reviews, Review::getService);
            double averageFood = calculateAverageRating(reviews, Review::getFood);
            double averageAtmosphere = calculateAverageRating(reviews, Review::getAtmosphere);

            restaurantPanelDto.setAverageService(averageService);
            restaurantPanelDto.setAverageFood(averageFood);
            restaurantPanelDto.setAverageAtmosphere(averageAtmosphere);

            List<Menu> menuItems = menuRepository.findByRestaurant(restaurantRepository.findById(id).get());
            restaurantPanelDto.setMenu(menuItems.stream().map(menuMapper::toDto).collect(Collectors.toList()));

            return restaurantPanelDto;
        }).orElse(null);
    }

    private double calculateAverageRating(List<Review> reviews, ToIntFunction<Review> ratingExtractor) {
        return (double) Math.round(reviews.stream().mapToInt(ratingExtractor).average().orElse(0) * 10) / 10;
    }

    public boolean verifyToken(Long restaurantId, String requestToken) {
        try {
            Restaurant restaurant = restaurantRepository.findById(restaurantId).get();
            return restaurant.getToken().equals(requestToken);
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}