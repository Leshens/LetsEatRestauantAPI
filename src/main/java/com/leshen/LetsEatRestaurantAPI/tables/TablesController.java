package com.leshen.LetsEatRestaurantAPI.tables;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/tables")
public class TablesController {
    private final TablesService tablesService;

    @Autowired
    public TablesController(TablesService tablesService) { this.tablesService = tablesService; }

    @GetMapping
    public List<Tables> getTables() {return tablesService.getTables(); }
}
