package com.leshen.LetsEatRestaurantAPI.Service.Mappers;

import com.leshen.LetsEatRestaurantAPI.Contract.RestaurantDto;
import com.leshen.LetsEatRestaurantAPI.Contract.RestaurantPanelDto;
import com.leshen.LetsEatRestaurantAPI.Model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RestaurantMapper {
    RestaurantMapper INSTANCE = Mappers.getMapper(RestaurantMapper.class);

    @Mapping(target = "restaurantId", ignore = true)
    @Mapping(target = "token", source = "restaurantDto.token")
    @Mapping(target = "restaurantName", source = "restaurantDto.restaurantName")
    @Mapping(target = "location", source = "restaurantDto.location")
    @Mapping(target = "restaurantCategory", source = "restaurantDto.restaurantCategory")
    @Mapping(target = "openingHours", source = "restaurantDto.openingHours")
    @Mapping(target = "photoLink", source = "restaurantDto.photoLink")
    @Mapping(target = "websiteLink", source = "restaurantDto.websiteLink")
    @Mapping(target = "longitude", source = "restaurantDto.longitude")
    @Mapping(target = "latitude", source = "restaurantDto.latitude")
    @Mapping(target = "phoneNumber", source = "restaurantDto.phoneNumber")
    Restaurant toEntity(RestaurantDto restaurantDto);

    RestaurantDto toDto(Restaurant restaurant);

    @Mapping(target = "restaurantId", ignore = true)
    @Mapping(target = "token", source = "restaurantDto.token")
    @Mapping(target = "restaurantName", source = "restaurantDto.restaurantName")
    @Mapping(target = "location", source = "restaurantDto.location")
    @Mapping(target = "restaurantCategory", source = "restaurantDto.restaurantCategory")
    @Mapping(target = "openingHours", source = "restaurantDto.openingHours")
    @Mapping(target = "photoLink", source = "restaurantDto.photoLink")
    @Mapping(target = "websiteLink", source = "restaurantDto.websiteLink")
    @Mapping(target = "longitude", source = "restaurantDto.longitude")
    @Mapping(target = "latitude", source = "restaurantDto.latitude")
    @Mapping(target = "phoneNumber", source = "restaurantDto.phoneNumber")
    Restaurant updateRestaurantFromDto(RestaurantDto restaurantDto, Restaurant restaurant);

    @Mapping(target = "restaurantId", source = "restaurantPanelDto.restaurantId")
    @Mapping(target = "restaurantName", source = "restaurantPanelDto.restaurantName")
    @Mapping(target = "location", source = "restaurantPanelDto.location")
    @Mapping(target = "openingHours", source = "restaurantPanelDto.openingHours")
    @Mapping(target = "photoLink", source = "restaurantPanelDto.photoLink")
    @Mapping(target = "phoneNumber", source = "restaurantPanelDto.phoneNumber")
    @Mapping(target = "websiteLink", source = "restaurantPanelDto.websiteLink")
    @Mapping(target = "longitude", source = "restaurantPanelDto.longitude")
    @Mapping(target = "latitude", source = "restaurantPanelDto.latitude")
    RestaurantPanelDto toPanelDto(Restaurant restaurant);
}