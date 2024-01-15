package com.leshen.LetsEatRestaurantAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan(basePackages = "com.leshen.LetsEatRestaurantAPI.Model")
@ComponentScan(basePackages = "com.leshen.LetsEatRestaurantAPI.Service.Mappers")
public class LetsEatRestaurantApiApplication {

	public static void main(String[] args) {

		SpringApplication.run(LetsEatRestaurantApiApplication.class, args);
		System.out.println("The soul of the Machine God surrounds thee.");
		System.out.println("The power of the Machine God invests thee");
		System.out.println("The hate of the Machine God drives thee.");
		System.out.println("The Machine God endows thee with life.");
		System.out.println("Live!");
	}

}
