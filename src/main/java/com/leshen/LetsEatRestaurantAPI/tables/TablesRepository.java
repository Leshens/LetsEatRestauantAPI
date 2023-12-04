package com.leshen.LetsEatRestaurantAPI.tables;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TablesRepository
        extends JpaRepository<Tables, Integer> {
}
