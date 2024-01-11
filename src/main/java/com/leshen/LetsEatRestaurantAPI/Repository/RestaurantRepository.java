package com.leshen.LetsEatRestaurantAPI.Repository;

import com.leshen.LetsEatRestaurantAPI.Model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Optional<Restaurant> findByToken(Long token);

    @Query(value = "SELECT * FROM restaurant " +
            "WHERE latitude BETWEEN :minLatitude AND :maxLatitude " +
            "AND longitude BETWEEN :minLongitude AND :maxLongitude",
            nativeQuery = true)
    List<Restaurant> findRestaurantsInArea(@Param("minLatitude") Double minLatitude,
                                           @Param("maxLatitude") Double maxLatitude,
                                           @Param("minLongitude") Double minLongitude,
                                           @Param("maxLongitude") Double maxLongitude);

}
