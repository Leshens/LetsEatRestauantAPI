package com.leshen.LetsEatRestaurantAPI.Repository;

import com.leshen.LetsEatRestaurantAPI.Model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Optional<Restaurant> findByToken(Long token);
    @Query(value = "SELECT r.*, "
            + " (6371 * acos( "
            + " cos(radians(:search_latitude)) * cos(radians(r.latitude)) "
            + " * cos(radians(r.longitude) - radians(:search_longitude)) "
            + " + sin(radians(:search_latitude)) * sin(radians(r.latitude)) "
            + " ) "
            + " ) AS distance "
            + " FROM restaurant r "
            + " HAVING distance <= :radius_in_km "
            + " ORDER BY distance", nativeQuery = true)
    List<Restaurant> findRestaurantsInRadius(
            @Param("search_latitude") Double searchLatitude,
            @Param("search_longitude") Double searchLongitude,
            @Param("radius_in_km") Double radiusInKm);
}
