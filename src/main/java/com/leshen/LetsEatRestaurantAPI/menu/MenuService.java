package com.leshen.LetsEatRestaurantAPI.menu;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    public List<Menu> getMenu() {
        return List.of(
                new Menu(
                        1,
                        "Spaghetti",
                        "25 zł",
                        "https://s3.przepisy.pl/przepisy3ii/img/variants/800x0/proste-spaghetti-bolognese.jpg"
                )
        );
    }
}
