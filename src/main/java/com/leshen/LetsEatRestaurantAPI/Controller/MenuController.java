package com.leshen.LetsEatRestaurantAPI.Controller;

import com.leshen.LetsEatRestaurantAPI.Model.Menu;
import com.leshen.LetsEatRestaurantAPI.Repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000")
public class MenuController {

    @Autowired
    private MenuRepository menuRepository;

    @PostMapping("/menu")
    Menu newMenu(@RequestBody Menu newMenu){
        return menuRepository.save(newMenu);
    }

    @GetMapping("/menus")
    public ResponseEntity<List<Menu>> getAllMenus(){
        return new ResponseEntity<>(menuRepository.findAll(), HttpStatus.OK);
    }
    @GetMapping("/menu/{id}")
    public ResponseEntity<Menu> getById(@PathVariable long id) {

        Optional<Menu> menu = menuRepository.findById(id);
        if (menu.isPresent()) {
            return new ResponseEntity<>(menu.get(), HttpStatus.OK);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Menu not found"
            );
        }
    }
    @PutMapping("updateMenu/{id}")
    public ResponseEntity<Menu> updateMenu(@PathVariable long id,@RequestBody Menu menu) {
        Menu updateMenu = menuRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Menu not found"
                ));
        updateMenu.setToken(menu.getToken());
        updateMenu.setName(menu.getName());
        updateMenu.setPrice(menu.getPrice());

        menuRepository.save(updateMenu);

        return ResponseEntity.ok(updateMenu);
    }

    @DeleteMapping(value = "/deleteMenu/{id}")
    public ResponseEntity<Long> deleteDish(@PathVariable Long id) {

        if (!menuRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Menu not found"
            );
        }
        menuRepository.deleteById(id);
        return new ResponseEntity<>(id, HttpStatus.OK);

    }

}
