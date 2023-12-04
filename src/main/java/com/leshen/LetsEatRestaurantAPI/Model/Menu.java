package com.leshen.LetsEatRestaurantAPI.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data
@NoArgsConstructor
@AllArgsConstructor
public class Menu {
    @Id
    @GeneratedValue
    private Long MenuId;
    private Long RestaurantId;
    private Long Token;
    private String Name;
    private Number Price;
}
