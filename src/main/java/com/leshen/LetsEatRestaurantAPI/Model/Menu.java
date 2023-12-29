package com.leshen.LetsEatRestaurantAPI.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data
@NoArgsConstructor
@AllArgsConstructor
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long menuId;
    private Long restaurantId;
    private Long token;
    private String name;
    private Float price;
    @ManyToOne
    private Restaurant restaurant;
}