package com.restaurant.restful.server.request;

import lombok.Data;

@Data
public class ItemRequest {
	private String itemName;
	
	private Integer count;
}
