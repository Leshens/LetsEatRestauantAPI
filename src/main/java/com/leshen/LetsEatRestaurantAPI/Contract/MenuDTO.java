package com.leshen.LetsEatRestaurantAPI.Contract;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MenuDTO {

    private Long restaurantId;
    private Long token;
    private String name;
    private Float price;
}
