package com.leshen.LetsEatRestaurantAPI.Service.Mappers;

import com.leshen.LetsEatRestaurantAPI.Contract.RestaurantPanelDto;
import com.leshen.LetsEatRestaurantAPI.Model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RestaurantPanelMapper {
    RestaurantPanelMapper INSTANCE = Mappers.getMapper(RestaurantPanelMapper.class);
    @Mapping(target = "menu", ignore = true)
    @Mapping(target = "reviews", ignore = true)
    RestaurantPanelDto toDto(Restaurant restaurant);

    Restaurant toEntity(RestaurantPanelDto restaurantPanelDto);
}
