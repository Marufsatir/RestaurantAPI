package com.restaurant.restful.server.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="orders_items")
@IdClass(OrderItemsPK.class)
public class OrderItems {
	
	@ManyToOne(fetch=FetchType.LAZY)
	@Id
	@JoinColumns({@JoinColumn(name="order_id", referencedColumnName="order_id")})
	Order orders;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@Id
	@JoinColumns({@JoinColumn(name="item_day", referencedColumnName="item_day"), @JoinColumn(name="menu_item_restaurant_id", referencedColumnName="menu_item_restaurant_id"), @JoinColumn(name="name", referencedColumnName="name")})
	MenuItem menuItem;
	
	@Column(name="count_item")
	int count;
}