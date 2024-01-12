package com.leshen.LetsEatRestaurantAPI.Service.Mappers;

import com.leshen.LetsEatRestaurantAPI.Contract.RestaurantListDto;
import com.leshen.LetsEatRestaurantAPI.Model.Restaurant;
import com.leshen.LetsEatRestaurantAPI.Model.Review;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RestaurantListMapper {
    RestaurantListMapper INSTANCE = Mappers.getMapper(RestaurantListMapper.class);

    @Mapping(source = "reviews", target = "stars", qualifiedByName = "calculateStars")
    RestaurantListDto toEntity(Restaurant restaurant);

    @Named("calculateStars")
    default Short calculateStars(List<Review> reviews) {
        if (reviews == null || reviews.isEmpty()) {
            return 0;
        }

        double averageRating = reviews.stream()
                .mapToInt(Review::getRating)
                .average()
                .orElse(0);

        return (short) Math.round(averageRating);
    }

    @Named("toDto")
    @Mapping(source = "reviews", target = "stars", qualifiedByName = "calculateStars")
    RestaurantListDto toDto(Restaurant restaurant);

    @IterableMapping(qualifiedByName = "toDto")
    @Mapping(source = "reviews", target = "stars", qualifiedByName = "calculateStars")
    List<RestaurantListDto> toDtoList(List<Restaurant> restaurants);
}
