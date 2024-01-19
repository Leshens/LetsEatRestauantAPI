package com.leshen.LetsEatRestaurantAPI.Controller;

import com.leshen.LetsEatRestaurantAPI.Contract.MenuDto;
import com.leshen.LetsEatRestaurantAPI.Service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/menus")
public class MenuController {

    private final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @PostMapping
    public ResponseEntity<MenuDto> newMenu(@RequestBody MenuDto newMenuDto) {
        MenuDto createdMenu = menuService.createMenu(newMenuDto);
        return new ResponseEntity<>(createdMenu, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<MenuDto>> getAllMenus(){
        List<MenuDto> menus = menuService.getAllMenus();
        return new ResponseEntity<>(menus, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<MenuDto> getById(@PathVariable long id) {
        MenuDto menu = menuService.getMenuById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Menu not found"));
        return new ResponseEntity<>(menu, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<MenuDto> updateMenu(
            @PathVariable long id,
            @RequestBody MenuDto menuDto,
            @RequestHeader("Authorization") String requestToken) {
        try {
            if (!menuService.verifyToken(id, requestToken)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            MenuDto updatedMenu = menuService.updateMenu(id, menuDto, requestToken);
            return ResponseEntity.ok(updatedMenu);
        } catch (RuntimeException e) {
            if (e instanceof NoSuchElementException) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MenuDto> patchMenu(
            @PathVariable Long id,
            @RequestBody MenuDto menuDto,
            @RequestHeader("Authorization") String requestToken) {
        try {
            if (!menuService.verifyToken(id, requestToken)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            MenuDto patchedMenu = menuService.patchMenu(id, menuDto);
            return ResponseEntity.ok(patchedMenu);
        } catch (RuntimeException e) {
            if (e instanceof NoSuchElementException) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteMenu(
            @PathVariable Long id,
            @RequestHeader("Authorization") String requestToken) {
        try {
            if (!menuService.verifyToken(id, requestToken)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            menuService.deleteMenu(id);
            return new ResponseEntity<>(id, HttpStatus.OK);
        } catch (RuntimeException e) {
            if (e instanceof NoSuchElementException) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

    }

}
