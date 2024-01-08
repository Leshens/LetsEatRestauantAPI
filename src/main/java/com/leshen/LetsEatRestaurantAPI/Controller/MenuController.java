package com.leshen.LetsEatRestaurantAPI.Controller;

import com.leshen.LetsEatRestaurantAPI.Contract.MenuDto;
import com.leshen.LetsEatRestaurantAPI.Service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class MenuController {

    private final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @PostMapping("/menu")
    public ResponseEntity<MenuDto> newMenu(@RequestBody MenuDto newMenuDto) {
        MenuDto createdMenu = menuService.createMenu(newMenuDto);
        return new ResponseEntity<>(createdMenu, HttpStatus.CREATED);
    }
    @GetMapping("/menus")
    public ResponseEntity<List<MenuDto>> getAllMenus(){
        List<MenuDto> menus = menuService.getAllMenus();
        return new ResponseEntity<>(menus, HttpStatus.OK);
    }
    @GetMapping("/menu/{id}")
    public ResponseEntity<MenuDto> getById(@PathVariable long id) {
        MenuDto menu = menuService.getMenuById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Menu not found"));
        return new ResponseEntity<>(menu, HttpStatus.OK);
    }
    @PutMapping("updateMenu/{id}")
    public ResponseEntity<MenuDto> updateMenu(@PathVariable long id,@RequestBody MenuDto menuDto) {
        MenuDto updatedMenu = menuService.updateMenu(id, menuDto);
        return ResponseEntity.ok(updatedMenu);
    }

    @DeleteMapping(value = "/deleteMenu/{id}")
    public ResponseEntity<Long> deleteDish(@PathVariable Long id) {
        menuService.deleteMenu(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

}
