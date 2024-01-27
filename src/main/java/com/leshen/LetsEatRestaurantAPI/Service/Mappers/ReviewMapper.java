package com.leshen.LetsEatRestaurantAPI.Service.Mappers;

import com.leshen.LetsEatRestaurantAPI.Contract.ReviewDto;
import com.leshen.LetsEatRestaurantAPI.Model.Restaurant;
import com.leshen.LetsEatRestaurantAPI.Model.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReviewMapper {
    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);

    @Mapping(target = "reviewId", ignore = true)
    @Mapping(target = "token", source = "reviewDto.token")
    @Mapping(target = "comment", source = "reviewDto.comment")
    @Mapping(target = "date", expression = "java( java.time.LocalDate.now() )")
    @Mapping(target = "restaurant", source = "reviewDto.restaurantId")
    @Mapping(target = "service", source = "reviewDto.service")
    @Mapping(target = "food", source = "reviewDto.food")
    @Mapping(target = "atmosphere", source = "reviewDto.atmosphere")
    Review toEntity(ReviewDto reviewDto);


    @Mapping(target = "service", source = "service")
    @Mapping(target = "food", source = "food")
    @Mapping(target = "atmosphere", source = "atmosphere")
    ReviewDto toDto(Review review);

    @Mapping(target = "reviewId", ignore = true)
    @Mapping(target = "token", source = "reviewDto.token")
    @Mapping(target = "comment", source = "reviewDto.comment")
    @Mapping(target = "date", expression = "java( java.time.LocalDate.now() )")
    @Mapping(target = "restaurant", source = "reviewDto.restaurantId")
    @Mapping(target = "service", source = "reviewDto.service")
    @Mapping(target = "food", source = "reviewDto.food")
    @Mapping(target = "atmosphere", source = "reviewDto.atmosphere")
    Review patchReviewFromDto(ReviewDto reviewDto, @MappingTarget Review review);

    Restaurant map(Long restaurantId);
}
