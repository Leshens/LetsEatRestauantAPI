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
    private String token;
    private String name;
    private Float price;
    @ManyToOne
    private Restaurant restaurant;

    @Override
    public String toString() {
        return "Menu{" +
                "menuId=" + menuId +
                ", token=" + token +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}