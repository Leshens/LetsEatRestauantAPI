package com.leshen.LetsEatRestaurantAPI.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/menu")
public class MenuController {

    private final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) { this.menuService = menuService; }

    @GetMapping
    public List<Menu> getMenu() {
        return menuService.getMenu();
    }

}
