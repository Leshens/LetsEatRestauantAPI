package com.leshen.LetsEatRestaurantAPI.Controller;

import com.leshen.LetsEatRestaurantAPI.Contract.RestaurantDto;
import com.leshen.LetsEatRestaurantAPI.Service.TableService;
import com.leshen.LetsEatRestaurantAPI.Contract.TableDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/tables")
public class TablesController {

    @Autowired
    private TableService tableService;

    @PostMapping
    public ResponseEntity<Long> newTable(@RequestBody TableDto newTableDto) {
        Long id = tableService.createTable(newTableDto);
        return ResponseEntity.created(URI.create("/table/" + id)).build();
    }

    @GetMapping
    public ResponseEntity<List<TableDto>> getAllTables() {
        List<TableDto> tableDtos = tableService.getAllTables();
        return new ResponseEntity<>(tableDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TableDto> getById(@PathVariable long id) {
        Optional<TableDto> tableDto = tableService.getTableById(id);
        return tableDto.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Table not found"));
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<TableDto> updateTables(@PathVariable long id, @RequestBody TableDto tableDto) {
//        tableService.updateTable(id, tableDto);
//        return ResponseEntity.ok().build();
//    }

    @PatchMapping("/{id}")
    public ResponseEntity<TableDto> patchTable(
            @PathVariable Long id,
            @RequestBody TableDto tableDto,
            @RequestHeader("Authorization") Long requestToken) {
        try {
            if (!tableService.verifyToken(id, requestToken)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            TableDto patchedTable = tableService.patchTable(id, tableDto);
            return ResponseEntity.ok(patchedTable);

        } catch (RuntimeException e) {
            if (e instanceof NoSuchElementException) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Long> deleteTable(
            @PathVariable Long id,
            @RequestHeader("Authorization") Long requestToken) {
        try {
            if (!tableService.verifyToken(id, requestToken)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            tableService.deleteTable(id);
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
