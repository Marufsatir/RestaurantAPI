package com.restaurant.restful.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.restaurant.restful.server.entity.Orders;
import com.restaurant.restful.server.request.CustomerRequest;
import com.restaurant.restful.server.request.OrderRequest;
import com.restaurant.restful.server.response.CustomerResponse;
import com.restaurant.restful.server.service.UserService;


@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	UserService userService;
	

	@PostMapping
	public CustomerResponse createCustomer(@RequestBody CustomerRequest customerRequest)
	{
		try {
			CustomerResponse customerResponse = userService.createCustomer(customerRequest);
			return customerResponse;
		}catch(Exception e)
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is a user with same user id!!!");
		}
	}
	
	@PostMapping("/order")
	public Orders createOrder(@RequestBody OrderRequest orderRequest) 
	{
		try {
			Orders order = userService.createOrder(orderRequest);
			return order;
		}catch(Exception e)
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong input!!!");
		}
	}
	
	@GetMapping("/order/{customerId}")
	public List<Orders> getOrder(@PathVariable String customerId)
	{
		return userService.getOrderByCustomerId(customerId);
	}
	
	@GetMapping("/order/{customerId}/{id}")
	public Orders getOrderById(@PathVariable Integer id, @PathVariable String customerId)
	{
		return userService.getOrderByIdCustomer(customerId, id);
	}
}
