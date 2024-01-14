package com.leshen.LetsEatRestaurantAPI.Service.Mappers;

import com.leshen.LetsEatRestaurantAPI.Contract.MenuDto;
import com.leshen.LetsEatRestaurantAPI.Contract.RestaurantPanelDto;
import com.leshen.LetsEatRestaurantAPI.Model.Menu;
import com.leshen.LetsEatRestaurantAPI.Model.Restaurant;
import com.leshen.LetsEatRestaurantAPI.Model.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface RestaurantPanelMapper {
    RestaurantPanelMapper INSTANCE = Mappers.getMapper(RestaurantPanelMapper.class);
    MenuMapper menuMapper = MenuMapper.INSTANCE;
    @Mapping(target = "menu", source = "restaurant", qualifiedByName = "mapMenuItems")
    @Mapping(target = "reviews", ignore = true)
    @Mapping(target = "averageService", source = "restaurant", qualifiedByName = "calculateAverageService")
    @Mapping(target = "averageFood", source = "restaurant", qualifiedByName = "calculateAverageFood")
    @Mapping(target = "averageAtmosphere", source = "restaurant", qualifiedByName = "calculateAverageAtmosphere")
    RestaurantPanelDto toDto(Restaurant restaurant);

    Restaurant toEntity(RestaurantPanelDto restaurantPanelDto);

    @Named("calculateAverageService")
    default double calculateAverageService(Restaurant restaurant) {
        List<Review> reviews = restaurant.getReviews();
        if (reviews == null || reviews.isEmpty()) {
            return 0.0;
        }

        double sum = reviews.stream().mapToDouble(Review::getService).sum();
        return sum / reviews.size();
    }

    @Named("calculateAverageFood")
    default double calculateAverageFood(Restaurant restaurant) {
        List<Review> reviews = restaurant.getReviews();
        if (reviews == null || reviews.isEmpty()) {
            return 0.0;
        }

        double sum = reviews.stream().mapToDouble(Review::getFood).sum();
        return sum / reviews.size();
    }

    @Named("calculateAverageAtmosphere")
    default double calculateAverageAtmosphere(Restaurant restaurant) {
        List<Review> reviews = restaurant.getReviews();
        if (reviews == null || reviews.isEmpty()) {
            return 0.0;
        }

        double sum = reviews.stream().mapToDouble(Review::getAtmosphere).sum();
        return sum / reviews.size();
    }

    @Named("mapMenuItems")
    default List<MenuDto> mapMenuItems(Restaurant restaurant) {
        List<Menu> menuItems = restaurant.getMenu();
        if (menuItems == null || menuItems.isEmpty()) {
            return Collections.emptyList();
        }

        return menuItems.stream().map(menuMapper::toDto).collect(Collectors.toList());
    }
}
