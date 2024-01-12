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

    @Query("SELECT r FROM Restaurant r WHERE " +
            "FUNCTION('acos', FUNCTION('sin', FUNCTION('radians', :latitude)) * FUNCTION('sin', FUNCTION('radians', r.latitude)) + " +
            "FUNCTION('cos', FUNCTION('radians', :latitude)) * FUNCTION('cos', FUNCTION('radians', r.latitude)) * " +
            "FUNCTION('cos', FUNCTION('radians', :longitude) - FUNCTION('radians', r.longitude))) * 6371 <= :radius")
    List<Restaurant> findRestaurantsInRadius(@Param("latitude") double latitude,
                                             @Param("longitude") double longitude,
                                             @Param("radius") double radius);
}
