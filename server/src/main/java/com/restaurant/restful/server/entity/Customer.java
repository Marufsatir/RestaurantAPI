package com.restaurant.restful.server.entity;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Data;

@Entity
@Data
@PrimaryKeyJoinColumn(name = "customer_id")
public class Customer extends User{
	
	@OneToMany(targetEntity=com.restaurant.restful.server.entity.Order.class, mappedBy="customer")
	private Set<Order> orders;
	
}
