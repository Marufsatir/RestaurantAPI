package com.restaurant.restful.server.request;

import lombok.Data;

@Data
public class CustomerRequest {
	private String customerId;
	
	private String adress;
}
