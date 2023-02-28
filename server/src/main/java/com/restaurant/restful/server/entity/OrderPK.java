package com.restaurant.restful.server.entity;

import java.io.Serializable;

public class OrderPK implements Serializable{
	
	private String customer;
	
	private Integer orderId;
	//private String restaurant;
	
	public OrderPK() {}
	
	public OrderPK(String customer, Integer orderId)
	{
		this.customer = customer;
		this.orderId = orderId;
		//this.restaurant = restaurant;
	}
	
	public boolean equals(Object object)
	{
		if(object instanceof OrderPK)
		{
			OrderPK orderPK = (OrderPK) object;
			if(orderPK.customer.equals(this.customer) && orderPK.orderId == this.orderId)
				return true;
			else
				return false;
		}else
		{
			return false;
		}
	}
	
	public int hashCode()
	{
		return (int)(this.customer.hashCode() + this.orderId);
	}
}
