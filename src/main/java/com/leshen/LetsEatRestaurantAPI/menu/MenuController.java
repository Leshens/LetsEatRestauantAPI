package com.leshen.LetsEatRestaurantAPI.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/menu")
public class MenuController {

    private final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService; }

    @GetMapping
    public List<Menu> getMenu() {
        return menuService.getMenu();
    }

    @PostMapping
    public void registerNewDish(@RequestBody Menu menu){
        menuService.addNewDish(menu);
    }

    @DeleteMapping(path = "{menuId}")
    public void deleteDish(
            @PathVariable("menuId") Integer menuId) {
        menuService.deleteDish(menuId);
    }

    @PutMapping(path = "{menuId}")
    public void updateDish(
            @PathVariable("menuId") Integer menuId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String price,
            @RequestParam(required = false) String photo) {
        menuService.updateDish(menuId, name , price, photo);
    }
}
