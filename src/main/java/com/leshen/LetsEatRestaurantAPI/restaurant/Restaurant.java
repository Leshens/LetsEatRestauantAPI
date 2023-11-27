package com.leshen.LetsEatRestaurantAPI.restaurant;

import java.util.Arrays;
import java.util.List;

public class Restaurant {
    private Integer id;
    private String userId;
    private String restaurantName;
//    private List<Integer>[] tables;
    private String location;

    public Restaurant() {
    }

    public Restaurant(Integer id, String userId, String restaurantName,
//                      List<Integer>[] tables,
                      String location) {
        this.id = id;
        this.userId = userId;
        this.restaurantName = restaurantName;
//        this.tables = tables;
        this.location = location;
    }

    public Restaurant(String userId, String restaurantName,
//                      List<Integer>[] tables,
                      String location) {
        this.userId = userId;
        this.restaurantName = restaurantName;
//        this.tables = tables;
        this.location = location;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

//    public List<Integer>[] getTables() {
//        return tables;
//    }
//
//    public void setTables(List<Integer>[] tables) {
//        this.tables = tables;
//    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", userId=" + userId +
                ", restaurantName='" + restaurantName + '\'' +
//                ", tables=" + Arrays.toString(tables) +
                ", location='" + location + '\'' +
                '}';
    }
}
