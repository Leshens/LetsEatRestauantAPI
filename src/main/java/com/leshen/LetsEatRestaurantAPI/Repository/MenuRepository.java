package com.leshen.LetsEatRestaurantAPI.Repository;

import com.leshen.LetsEatRestaurantAPI.Model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {

}
