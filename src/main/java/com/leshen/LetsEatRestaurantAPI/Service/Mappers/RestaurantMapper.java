package com.leshen.LetsEatRestaurantAPI.Service.Mappers;

import com.leshen.LetsEatRestaurantAPI.Contract.RestaurantDto;
import com.leshen.LetsEatRestaurantAPI.Model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RestaurantMapper {
    RestaurantMapper INSTANCE = Mappers.getMapper(RestaurantMapper.class);

    @Mapping(target = "restaurantId", source = "restaurantDto.restaurantId")
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
    List<RestaurantDto> toDtoList(List<Restaurant> restaurants);

    @Mapping(target = "restaurantId", source = "restaurantDto.restaurantId")
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

    @Mapping(target = "restaurantId", source = "restaurantDto.restaurantId")
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
    Restaurant patchRestaurantFromDto(RestaurantDto restaurantDto, @MappingTarget Restaurant restaurant);
    Restaurant map(Long restaurantId);
}