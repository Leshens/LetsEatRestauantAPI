package com.leshen.LetsEatRestaurantAPI.menu;

import com.leshen.LetsEatRestaurantAPI.restaurant.Restaurant;
import com.leshen.LetsEatRestaurantAPI.restaurant.RestaurantRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class MenuConfig {

    @Bean
    CommandLineRunner commandLineRunnerMenu(MenuRepository repository){
        return args -> {
            Menu spaghetti = new Menu(
                    "Spaghetti",
                    "25 zł",
                    "https://s3.przepisy.pl/przepisy3ii/img/variants/800x0/proste-spaghetti-bolognese.jpg"
            );

            Menu pizza = new Menu(
                    "Pizza",
                    "30 zł",
                    "Pizza.jpg"
            );
            repository.saveAll(
                    List.of(spaghetti, pizza)
            );
        };
    }
}
