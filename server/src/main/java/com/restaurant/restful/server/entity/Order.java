package com.restaurant.restful.server.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="orders")
//@IdClass(OrderPK.class)
@Data
public class Order {

	@Id
	@Column(name="order_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer orderId;
	
	/*
	@ManyToOne(fetch= FetchType.LAZY)
	@Id
	@JoinColumn(name="restaurant_id", nullable=false)
	Restaurant restaurant;
	*/
	
	@ManyToOne(fetch= FetchType.EAGER)
	@JoinColumn(name="customer_id",  nullable=false)
	Customer customer;
	
	@OneToMany(targetEntity=com.restaurant.restful.server.entity.OrderItems.class ,mappedBy="orders")
	private Set<OrderItems> orderItems;
	
	public int hashCode()
	{
		return 13;
	}
}
