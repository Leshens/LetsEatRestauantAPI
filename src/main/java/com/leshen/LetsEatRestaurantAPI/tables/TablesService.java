package com.leshen.LetsEatRestaurantAPI.tables;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TablesService {

    private final TablesRepository tablesRepository;

    @Autowired
    public TablesService(TablesRepository tablesRepository) {
        this.tablesRepository = tablesRepository;
    }


    public List<Tables> getTables() {
        return tablesRepository.findAll();
    }
}