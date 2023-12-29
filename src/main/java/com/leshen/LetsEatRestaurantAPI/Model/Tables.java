package com.leshen.LetsEatRestaurantAPI.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data
@NoArgsConstructor
@AllArgsConstructor
public class Tables {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tableId;
    private Long token;
    private Long restaurantId;
    private Short twoOs;
    private Short fourOs;
    private Short sixOs;
    private Short eightOs;

    @ManyToOne
    private Restaurant restaurant;
}
