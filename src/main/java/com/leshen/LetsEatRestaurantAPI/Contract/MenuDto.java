package com.leshen.LetsEatRestaurantAPI.Contract;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
public class MenuDto {
    @Schema(description = "Unique identifier for the menu")
    private Long menuId;

    @Schema(description = "Identifier for the restaurant to which the menu belongs")
    private Long restaurantId;

    @Schema(description = "Token for authorization purposes", defaultValue = "your_token", example = "your_token")
    private String token;

    @Schema(description = "Name of the menu item", defaultValue = "Pizza", example = "Pizza")
    private String name;

    @Schema(description = "Price of the menu item", minimum = "0.01", example = "10.99", defaultValue = "21.37")
    private Float price;
}
