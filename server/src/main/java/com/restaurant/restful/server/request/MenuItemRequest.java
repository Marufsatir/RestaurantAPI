package com.restaurant.restful.server.request;

import lombok.Data;

@Data
public class MenuItemRequest {
	String name;
	
	String description;
	
	Double price;
}
