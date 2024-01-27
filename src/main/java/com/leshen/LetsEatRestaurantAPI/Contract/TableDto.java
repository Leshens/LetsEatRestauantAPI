package com.leshen.LetsEatRestaurantAPI.Contract;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TableDto {
    @Schema(description = "Unique identifier for the table")
    private Long tableId;

    @Schema(description = "Identifier for the restaurant to which the table belongs")
    private Long restaurantId;

    @Schema(description = "Token for authorization purposes", defaultValue = "your_token", example = "your_token")
    private String token;

    @Schema(description = "Size of the table", minimum = "2", example = "2", defaultValue = "2")
    private Short size;
}
