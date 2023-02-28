package com.restaurant.restful.server.entity;


import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="menuitem")
@IdClass(MenuItemPK.class)
@Data
public class MenuItem {
	
	@Id
	String name;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@Id
	@JoinColumns({@JoinColumn(name="item_day", referencedColumnName="day"), 
		@JoinColumn(name="menu_item_restaurant_id", referencedColumnName="menu_restaurant_id")})
	private Menu menu;
	
	String description;
	
	Double price;
	
	@OneToMany(targetEntity=com.restaurant.restful.server.entity.OrderItems.class ,mappedBy="menuItem")
	private Set<Order> orders;
}
