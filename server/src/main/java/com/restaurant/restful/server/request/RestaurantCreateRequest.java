package com.restaurant.restful.server.request;

import lombok.Data;

@Data
public class RestaurantCreateRequest {
	String userId;
	String adress;
	String name;
	String description;
}
