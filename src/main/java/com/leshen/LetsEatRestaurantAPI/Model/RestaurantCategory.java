package com.leshen.LetsEatRestaurantAPI.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RestaurantCategory {
    ITALIAN("Italian Cuisine"),
    CHINESE("Chinese Cuisine"),
    MEXICAN("Mexican Cuisine"),
    AMERICAN("American Cuisine"),
    INDIAN("Indian Cuisine"),
    JAPANESE("Japanese Cuisine"),
    FRENCH("French Cuisine"),
    OTHER("Other Cuisine");

    private final String displayName;
}
