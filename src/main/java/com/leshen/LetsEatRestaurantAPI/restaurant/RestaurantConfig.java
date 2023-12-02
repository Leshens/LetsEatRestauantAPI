package com.leshen.LetsEatRestaurantAPI.restaurant;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class RestaurantConfig {

    @Bean
    CommandLineRunner commandLineRunnerRestaurant(RestaurantRepository repository){
        return args -> {
           Restaurant restaurax = new Restaurant(
                    "1",
                    "Restaurax",
                    "Grunwaldzka 5"
            );

            Restaurant orzel = new Restaurant(
                    "2",
                    "Orzeł",
                    "Warszawska 6"
            );

            repository.saveAll(
                    List.of(restaurax, orzel)
            );
        };
    }
}
