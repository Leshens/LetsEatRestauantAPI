package com.leshen.LetsEatRestaurantAPI.Service.Mappers;

import com.leshen.LetsEatRestaurantAPI.Contract.RestaurantListDto;
import com.leshen.LetsEatRestaurantAPI.Model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RestaurantListMapper {
    RestaurantListMapper INSTANCE = Mappers.getMapper(RestaurantListMapper.class);

    @Mapping(source = "restaurantId", target = "restaurantId")
    @Mapping(source = "restaurantName", target = "restaurantName")
    @Mapping(source = "restaurantCategory", target = "restaurantCategory")
    @Mapping(source = "openingHours", target = "openingHours")
    @Mapping(source = "photoLink", target = "photoLink")
    @Mapping(source = "longitude", target = "longitude")
    @Mapping(source = "latitude", target = "latitude")
    @Mapping(source = "tables", target = "tables")
    @Mapping(source = "review", target = "stars")
    RestaurantListDto toEntity(Restaurant restaurant);

    @Mapping(source = "restaurantId", target = "restaurantId")
    @Mapping(source = "restaurantName", target = "restaurantName")
    @Mapping(source = "restaurantCategory", target = "restaurantCategory")
    @Mapping(source = "openingHours", target = "openingHours")
    @Mapping(source = "photoLink", target = "photoLink")
    @Mapping(source = "longitude", target = "longitude")
    @Mapping(source = "latitude", target = "latitude")
    @Mapping(source = "tables", target = "tables")
    @Mapping(source = "review", target = "stars")
    RestaurantListDto toDto(Restaurant restaurant);

    List<RestaurantListDto> toDtoList(List<Restaurant> restaurants);
}
