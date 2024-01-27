package com.leshen.LetsEatRestaurantAPI.Contract;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantPanelDto {

    @Schema(description = "Unique identifier for the restaurant")
    private Long restaurantId;

    @Schema(description = "Restaurant's name", defaultValue = "Example restaurant", example = "Example restaurant")
    private String restaurantName;

    @Schema(description = "Restaurant's location", defaultValue = "80-894 Gdansk, Targ Drzewny 9/11", example = "City, street name")
    private String location;

    @Schema(description = "Restaurant's opening hours", defaultValue = "10AM - 7PM", example = "10AM - 7PM")
    private String openingHours;

    @Schema(description = "Restaurant's photo link", defaultValue = "http://dummyimage.com/204x100.png/dddddd/000000", example = "http://dummyimage.com/204x100.png/dddddd/000000")
    private String photoLink;

    @Schema(description = "Phone number", defaultValue = "213791169", example = "213791169")
    private String phoneNumber;

    @Schema(description = "Restaurant's website link", defaultValue = "http://31.179.139.182:690/", example = "http://31.179.139.182:690/")
    private String websiteLink;

    @Schema(description = "Location longitude", defaultValue = "103.817356", example = "103.817356")
    private Double longitude;

    @Schema(description = "Location latitude", defaultValue = "25.60162", example = "25.60162")
    private Double latitude;

    @Schema(description = "Menu of the restaurant")
    private List<MenuDto> menu;

    @Schema(description = "Reviews of the restaurant")
    private List<ReviewDto> reviews;

    @Schema(description = "Calculated average rating of food in restaurant", minimum = "1", maximum = "5")
    private Double averageFood;

    @Schema(description = "Calculated average rating of service in restaurant", minimum = "1", maximum = "5")
    private Double averageService;

    @Schema(description = "Calculated average rating of atmosphere in restaurant", minimum = "1", maximum = "5")
    private Double averageAtmosphere;
}
