package com.leshen.LetsEatRestaurantAPI.Service;

import com.leshen.LetsEatRestaurantAPI.Contract.RestaurantDto;
import com.leshen.LetsEatRestaurantAPI.Model.Restaurant;
import com.leshen.LetsEatRestaurantAPI.Repository.RestaurantRepository;
import com.leshen.LetsEatRestaurantAPI.Service.Mappers.RestaurantMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper = RestaurantMapper.INSTANCE;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
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

}