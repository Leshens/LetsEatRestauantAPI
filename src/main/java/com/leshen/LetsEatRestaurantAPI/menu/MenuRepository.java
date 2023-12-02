package com.leshen.LetsEatRestaurantAPI.menu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MenuRepository
        extends JpaRepository<Menu, Integer> {

//    @Query("SELECT m FROM Menu m where m.name = ?1")
//    Optional<Menu> findMenuByName(String name);
}
