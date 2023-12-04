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

}
