package com.leshen.LetsEatRestaurantAPI.menu;

public class Menu {

    private Integer id;

    private String Name;

    private String Price;

    private String Photo;

    public Menu() {
    }

    public Menu(Integer id, String name, String price, String photo) {
        this.id = id;
        Name = name;
        Price = price;
        Photo = photo;
    }

    public Menu(String name, String price, String photo) {
        Name = name;
        Price = price;
        Photo = photo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getPhoto() {
        return Photo;
    }

    public void setPhoto(String photo) {
        Photo = photo;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", Name=" + Name +
                ", Price='" + Price + '\'' +
                ", Photo='" + Photo + '\'' +
                '}';
    }
}
