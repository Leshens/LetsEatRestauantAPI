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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
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
        System.out.println(latitude);
        System.out.println(longitude);
        System.out.println(radius);
        List<Restaurant> restaurants = restaurantRepository.findRestaurantsInRadius(latitude, longitude, radius);
        System.out.println("restaurants"+restaurants);

        // Fetch tables for each restaurant and map to TableDto
        List<RestaurantListDto> restaurantListDtos = restaurantListMapper.toDtoList(restaurants);
        for (int i = 0; i < restaurants.size(); i++) {
            List<TableDto> tables = tablesRepository.findByRestaurant(restaurants.get(i))
                    .stream()
                    .map(tablesMapper::toDto)
                    .collect(Collectors.toList());
            restaurantListDtos.get(i).setTables(tables);
        }
        System.out.println("restaurantListDtos"+restaurantListDtos);
        return restaurantListDtos;
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

    public Optional<RestaurantDto> getRestaurantByToken(long token) {
        return restaurantRepository.findByToken(token).map(restaurantMapper::toDto);
    }

    public RestaurantDto updateRestaurant(long id, RestaurantDto restaurantDto, Long requestToken) {
        Restaurant existingRestaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        restaurantMapper.updateRestaurantFromDto(restaurantDto, existingRestaurant);

        return restaurantMapper.toDto(restaurantRepository.save(existingRestaurant));
    }
    public RestaurantDto patchRestaurant(long id, RestaurantDto restaurantDto) {
        Restaurant existingRestaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

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
        return restaurantRepository.findById(id)
                .map(restaurantPanelMapper::toDto)
                .map(restaurantPanelDto -> {
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
                })
                .orElse(null);
    }

    private double calculateAverageRating(List<Review> reviews, ToIntFunction<Review> ratingExtractor) {
        return reviews.stream()
                .mapToInt(ratingExtractor)
                .average()
                .orElse(0);
    }

    public boolean verifyToken(Long restaurantId, Long requestToken) {
        try {
            Restaurant restaurant = restaurantRepository.findById(restaurantId).get();
            return restaurant.getToken().equals(requestToken);
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}