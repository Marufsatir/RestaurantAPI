package com.restaurant.restful.server.repos;

import lombok.Data;

@Data
public class MenuPKRequest {
	String restaurantId;
	
	Integer day;
}
