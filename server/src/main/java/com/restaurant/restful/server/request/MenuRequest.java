package com.restaurant.restful.server.request;

import java.util.List;

import lombok.Data;

@Data
public class MenuRequest {
	Integer dayNumber;
	
	String restaurantId;
	
	Integer currencyType;
	
	List<MenuItemRequest> menuItems;
	
	public List<MenuItemRequest> getMenuItems()
	{
		return menuItems;
	}
}
