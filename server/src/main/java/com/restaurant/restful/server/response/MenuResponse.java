package com.restaurant.restful.server.response;

import java.util.List;

import com.restaurant.restful.server.entity.Menu;
import com.restaurant.restful.server.request.MenuItemRequest;
import com.restaurant.restful.server.request.MenuRequest;

import lombok.Data;

@Data
public class MenuResponse {
	
	String restaurantName;
	
	Integer currencyType;
	
	Integer day;
	
	List<MenuItemRequest> items;
}
