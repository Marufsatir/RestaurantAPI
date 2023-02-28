package com.restaurant.restful.server.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class OrderItemsPK implements Serializable{
	
	
	private MenuItemPK menuItem;
	
	private Integer orders;
	
	public OrderItemsPK() {}
	
	public OrderItemsPK(MenuItemPK menuItem, Integer orders)
	{
		this.menuItem = menuItem;
		this.orders = orders;
		//this.restaurant = restaurant;
	}
	
	public boolean equals(Object object)
	{
		if(object instanceof OrderItemsPK)
		{
			OrderItemsPK orderPK = (OrderItemsPK) object;
			if(orderPK.menuItem.equals(this.menuItem) && orderPK.orders == this.orders)
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
		return (int)(this.menuItem.hashCode() + this.orders);
	}
}
