package com.leshen.LetsEatRestaurantAPI.Repository;

import com.leshen.LetsEatRestaurantAPI.Model.Restaurant;
import com.leshen.LetsEatRestaurantAPI.Model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByRestaurant(Restaurant restaurant);

    @Query("SELECT AVG(r.food), AVG(r.service), AVG(r.atmosphere) FROM Review r WHERE r.restaurant = :restaurant")
    Optional<Double> findAverageRatingByRestaurant(@Param("restaurant") Restaurant restaurant);

    @Query("SELECT r.token, MAX(r.date) FROM Review r WHERE r.token = :token")
    Optional<Object[]> findLatestReviewTokenDate(@Param("token") String token);

    List<Review> findByTokenAndDateAfter(String token, LocalDateTime date);
}
