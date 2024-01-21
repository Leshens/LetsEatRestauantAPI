package com.leshen.LetsEatRestaurantAPI.Contract;

import com.leshen.LetsEatRestaurantAPI.Model.RestaurantCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Data
public class RestaurantListDto {

    @Schema(description = "Unique identifier for the restaurant")
    private Long restaurantId;

    @Schema(description = "Restaurant's name", defaultValue = "Example restaurant", example = "Example restaurant")
    private String restaurantName;

    @Schema(description = "Restaurant's category")
    private RestaurantCategory restaurantCategory;

    @Schema(description = "Restaurant's opening hours", defaultValue = "10AM - 7PM", example = "10AM - 7PM")
    private String openingHours;

    @Schema(description = "Restaurant's photo link", defaultValue = "http://dummyimage.com/204x100.png/dddddd/000000", example = "http://dummyimage.com/204x100.png/dddddd/000000")
    private String photoLink;

    @Schema(description = "Location longitude", defaultValue = "103.817356", example = "103.817356")
    private Double longitude;

    @Schema(description = "Location latitude", defaultValue = "25.60162", example = "25.60162")
    private Double latitude;

    @Schema(description = "List of available tables")
    private List<TableDto> tables;


    @Setter
    @Getter
    @Schema(description = "Calculated rating of restaurant")
    private double stars;
}