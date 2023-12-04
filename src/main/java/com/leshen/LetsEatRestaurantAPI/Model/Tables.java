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
public class Tables {
    @Id
    @GeneratedValue
    private Long TableId;
    private Long Token;
    private Long RestaurantId;
    private String Name;
    private Short twoOs;
    private Short fourOs;
    private Short sixOs;
    private Short eightOs;
}
