package com.leshen.LetsEatRestaurantAPI.Repository;

import com.leshen.LetsEatRestaurantAPI.Model.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MockMenuRepository {
    private final List<Menu> menus = new ArrayList<>();

    public Menu save(Menu menu) {
        menus.add(menu);
        return menu;
    }

    public List<Menu> findAll() {
        return new ArrayList<>(menus);
    }

    public Optional<Menu> findById(Long id) {
        return menus.stream().filter(m -> m.getMenuId().equals(id)).findFirst();
    }
}
