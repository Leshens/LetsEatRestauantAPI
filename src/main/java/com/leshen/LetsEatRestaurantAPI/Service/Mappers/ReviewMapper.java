package com.leshen.LetsEatRestaurantAPI.Service.Mappers;

import com.leshen.LetsEatRestaurantAPI.Contract.ReviewDto;
import com.leshen.LetsEatRestaurantAPI.Model.Restaurant;
import com.leshen.LetsEatRestaurantAPI.Model.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ReviewMapper {
    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);

    @Mapping(target = "reviewId", ignore = true)
    @Mapping(target = "token", source = "reviewDto.token")
    @Mapping(target = "comment", source = "reviewDto.comment")
    @Mapping(target = "date", source = "reviewDto.date")
    @Mapping(target = "restaurant", source = "reviewDto.restaurantId")
    @Mapping(target = "rating", source = "reviewDto.rating")
    Review toEntity(ReviewDto reviewDto);

    ReviewDto toDto(Review review);
}
