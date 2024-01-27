package com.leshen.LetsEatRestaurantAPI.Contract;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TableDto {
    private Long tableId;
    private Long restaurantId;
    private String token;
    private Short size;
}
