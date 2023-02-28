package com.restaurant.restful.server.repos;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.restaurant.restful.server.entity.Orders;

public interface OrdersRepo extends MongoRepository<Orders, Integer>{
	
	List<Orders> findBycustomerId(String customerId);
	List<Orders> findByrestaurantId(String restaurantId);
 }
