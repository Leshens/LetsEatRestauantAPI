package com.leshen.LetsEatRestaurantAPI.Repository;

import com.leshen.LetsEatRestaurantAPI.Model.Tables;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MockTablesRepository {
    private final List<Tables> tables = new ArrayList<>();

    public Tables save(Tables table) {
        tables.add(table);
        return table;
    }

    public List<Tables> findAll() {
        return new ArrayList<>(tables);
    }

    public Optional<Tables> findById(Long id) {
        return tables.stream().filter(t -> t.getTableId().equals(id)).findFirst();
    }

}
