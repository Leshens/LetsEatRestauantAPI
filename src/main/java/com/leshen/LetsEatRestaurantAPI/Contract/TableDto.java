package com.leshen.LetsEatRestaurantAPI.Contract;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TableDto {
    private Long tableId;
    private Long token;
    private Short size;
}
