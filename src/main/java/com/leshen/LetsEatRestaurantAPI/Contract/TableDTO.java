package com.leshen.LetsEatRestaurantAPI.Contract;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TableDTO {
    private Long token;
    private Long restaurantId;
    private Short twoOs;
    private Short fourOs;
    private Short sixOs;
    private Short eightOs;
}
