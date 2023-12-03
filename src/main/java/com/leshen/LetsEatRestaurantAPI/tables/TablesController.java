package com.leshen.LetsEatRestaurantAPI.tables;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/tables")
public class TablesController {
    private final TablesService tablesService;

    @Autowired
    public TablesController(TablesService tablesService) { this.tablesService = tablesService; }

    @GetMapping
    public List<Tables> getTables() {return tablesService.getTables(); }

//    @PutMapping(path = "{tablesId}")
//    public void updateTables(
//            @PathVariable("tablesId") Integer tablesId,
//            @RequestParam(required = false) Integer twoOs,
//            @RequestParam(required = false) Integer fourOs,
//            @RequestParam(required = false) Integer sixOs,
//            @RequestParam(required = false) Integer eightOs){
//        TablesService.updateTables(tablesId, twoOs , fourOs, sixOs, eightOs );
//    }
}
