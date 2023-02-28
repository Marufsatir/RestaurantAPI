package com.restaurant.restful.server.request;

import java.util.List;

import lombok.Data;

@Data
public class OrderRequest {
	private Integer day;
	
	private String restaurantId;
	
	private String customerId;
	
	private List<ItemRequest> items;
}
