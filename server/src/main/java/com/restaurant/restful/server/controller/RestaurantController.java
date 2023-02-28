package com.restaurant.restful.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.restaurant.restful.server.entity.Orders;
import com.restaurant.restful.server.repos.MenuPKRequest;
import com.restaurant.restful.server.request.MenuRequest;
import com.restaurant.restful.server.request.RestaurantCreateRequest;
import com.restaurant.restful.server.response.MenuResponse;
import com.restaurant.restful.server.response.RestaurantResponse;
import com.restaurant.restful.server.service.UserService;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
	
	@Autowired
	UserService userService;
	
	@PostMapping
	public RestaurantResponse createRestaurant(@RequestBody RestaurantCreateRequest restaurantCreateRequest)
	{
		try {
			RestaurantResponse response = userService.createRestaurant(restaurantCreateRequest);
			return response;
		}catch(Exception e){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is a user with same user id!!!");
		}
	}
	
	@PostMapping("/menu")
	public MenuResponse createMenu(@RequestBody MenuRequest menuRequest)
	{
		try {
			MenuResponse menuResponse = userService.createMenu(menuRequest);
			return menuResponse;
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not valid input!");
		}
	}
	
	@PutMapping("/menu")
	public MenuResponse updateMenu(@RequestBody MenuRequest menuRequest)
	{
		try {
			MenuResponse menuResponse = userService.updateMenu(menuRequest);
			return menuResponse;
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not valid input!");
		}
	}
	
	@RequestMapping(value="/menu", method=RequestMethod.DELETE)
	public boolean deleteMenu(@RequestBody MenuPKRequest menuRequest)
	{
		return userService.deleteMenu(menuRequest);
	}
	
	@GetMapping("/menu/{day}")
	public List<MenuResponse> getMenuSpecificDay(@PathVariable Integer day)
	{
		return userService.getMenuSpecificDay(day);
	}
	
	@GetMapping("/order/{restaurantId}")
	public List<Orders> getOrder(@PathVariable String restaurantId)
	{
		return userService.getOrderByRestaurantId(restaurantId);
	}
	
	@GetMapping("/order/{restaurantId}/{id}")
	public Orders getOrderById(@PathVariable Integer id, @PathVariable String restaurantId)
	{
		return userService.getOrderByIdRestaurant(restaurantId, id);
	}
}
