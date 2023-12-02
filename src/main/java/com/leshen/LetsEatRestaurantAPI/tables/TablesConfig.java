package com.leshen.LetsEatRestaurantAPI.tables;

import com.leshen.LetsEatRestaurantAPI.restaurant.Restaurant;
import com.leshen.LetsEatRestaurantAPI.restaurant.RestaurantRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class TablesConfig {

    @Bean
    CommandLineRunner commandLineRunnerTables(TablesRepository repository){
        return args -> {
            Tables tables = new Tables(
                    "Stoliki",
                    1,
                    1,
                    1,
                    1
            );
            repository.saveAll(
                    List.of(tables)
            );
        };
    }
}
