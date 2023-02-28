package com.restaurant.restful.server.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurant.restful.server.entity.Restaurant;

public interface RestaurantRepo extends JpaRepository<Restaurant, String> {

}
