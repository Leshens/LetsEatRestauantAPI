package com.leshen.LetsEatRestaurantAPI.Controller;

import com.leshen.LetsEatRestaurantAPI.Model.Tables;
import com.leshen.LetsEatRestaurantAPI.Repository.TablesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000")
public class TablesController {

    @Autowired
    private TablesRepository tablesRepository;

    @PostMapping("/table")
    Tables newTables(@RequestBody Tables newTables){
        return tablesRepository.save(newTables);
    }

    @GetMapping("/tables")
    public ResponseEntity<List<Tables>> getAllTables(){
        return new ResponseEntity<>(tablesRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/table/{id}")
    public ResponseEntity<Tables> getById(@PathVariable long id) {

        Optional<Tables> tables = tablesRepository.findById(id);
        if (tables.isPresent()) {
            return new ResponseEntity<>(tables.get(), HttpStatus.OK);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Table not found"
            );
        }
    }

    @PutMapping("updateTables/{id}")
    public ResponseEntity<Tables> updateTables(@PathVariable long id,@RequestBody Tables tables) {
        Tables updateTables = tablesRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Table not found"
                ));

        updateTables.setToken(tables.getToken());
        updateTables.setTwoOs(tables.getTwoOs());
        updateTables.setFourOs(tables.getFourOs());
        updateTables.setSixOs(tables.getSixOs());
        updateTables.setEightOs(tables.getEightOs());

        tablesRepository.save(updateTables);

        return ResponseEntity.ok(updateTables);
    }

    @DeleteMapping(value = "/deleteTable/{id}")
    public ResponseEntity<Long> deleteTable(@PathVariable Long id) {

        if (!tablesRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Table not found"
            );
        }
        tablesRepository.deleteById(id);
        return new ResponseEntity<>(id, HttpStatus.OK);

    }
}
