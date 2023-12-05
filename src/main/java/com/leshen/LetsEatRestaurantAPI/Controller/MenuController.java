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

    @PostMapping("/Menu")
    Menu newMenu(@RequestBody Menu newMenu){
        return menuRepository.save(newMenu);
    }

    @GetMapping("/Menu")
    public ResponseEntity<List<Menu>> getAllMenu(){
        return new ResponseEntity<>(menuRepository.findAll(), HttpStatus.OK);
    }
    @GetMapping("/Menu/{id}")
    public ResponseEntity<Menu> getById(@PathVariable long id) {

        Optional<Menu> menu = menuRepository.findById(id);
        if (menu.isPresent()) {
            return new ResponseEntity<>(menu.get(), HttpStatus.OK);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Dish not found"
            );
        }
    }
    @PutMapping("updateDish/{id}")
    public ResponseEntity<Menu> updateDish(@PathVariable long id,@RequestBody Menu menu) {
        Menu updateDish = menuRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Dish not found"
                ));

        updateDish.setName(menu.getName());
        updateDish.setPrice(menu.getPrice());

        menuRepository.save(updateDish);

        return ResponseEntity.ok(updateDish);
    }

    @DeleteMapping(value = "/deleteDish/{id}")
    public ResponseEntity<Long> deleteDish(@PathVariable Long id) {

        if (!menuRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Dish not found"
            );
        }
        menuRepository.deleteById(id);
        return new ResponseEntity<>(id, HttpStatus.OK);

    }

}
