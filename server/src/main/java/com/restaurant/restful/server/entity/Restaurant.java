package com.restaurant.restful.server.entity;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="restaurant")
@Data
@PrimaryKeyJoinColumn(name = "restaurant_id")
public class Restaurant extends User{

	String name;
	
	String description;
	
	//String address;
	
	@OneToMany(targetEntity=com.restaurant.restful.server.entity.Menu.class ,mappedBy="restaurant")
	private Set<Menu> menus;
	
	/*
	@OneToMany(targetEntity=com.restaurant.restful.server.entity.Order.class ,mappedBy="restaurant")
	private Set<Order> orders;
	*/
	
}
