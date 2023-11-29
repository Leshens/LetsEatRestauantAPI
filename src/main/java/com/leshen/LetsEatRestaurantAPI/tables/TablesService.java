package com.leshen.LetsEatRestaurantAPI.tables;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TablesService {

    public List<Tables> getTables(){
        return List.of(
                new Tables(
                        1,
                        "Stoliki",
                        1,
                        1,
                        1,
                        1
                )
        );
    }
}
