package com.leshen.LetsEatRestaurantAPI.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity @Data
@Table(name = "restaurant")
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long restaurantId;
    private Long token;
    private String restaurantName;
    private String location;

    @Enumerated(EnumType.STRING)
    @Column(name = "restaurant_category", length = 32)
    private RestaurantCategory restaurantCategory;

    private String openingHours;
    private String photoLink;
    private String websiteLink;
    private Double longitude;
    private Double latitude;
    private String phoneNumber;

    @OneToMany(mappedBy = "restaurant")
    private List<Menu> menu;
    @OneToMany(mappedBy = "restaurant")
    private List<Tables> tables;
    @OneToMany(mappedBy ="restaurant")
    private List<Review> reviews;
    @Override
    public String toString() {
        return "Restaurant{" +
                "restaurantId=" + restaurantId +
                ", token=" + token +
                ", restaurantName='" + restaurantName + '\'' +
                ", location='" + location + '\'' +
                ", restaurantCategory=" + restaurantCategory +
                ", openingHours='" + openingHours + '\'' +
                ", photoLink='" + photoLink + '\'' +
                ", websiteLink='" + websiteLink + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
