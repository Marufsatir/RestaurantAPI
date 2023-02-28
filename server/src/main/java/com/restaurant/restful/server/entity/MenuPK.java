package com.restaurant.restful.server.entity;

import java.io.Serializable;


public class MenuPK implements Serializable{
	
	private Days day;
	
	private String restaurant;
	
	public MenuPK() {}
	
	public MenuPK(Days day, String restaurant)
	{
		this.day = day;
		this.restaurant = restaurant;
	}
	
	public MenuPK(Integer day, String restaurant)
	{
		this.day = Days.values()[day];
		this.restaurant = restaurant;
	}
	
	public boolean equals(Object object)
	{
		if(object instanceof MenuPK)
		{
			MenuPK menuPK = (MenuPK) object;
			if(menuPK.day == this.day && menuPK.restaurant.equals(this.restaurant))
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
		return (int)(day.ordinal() + this.restaurant.hashCode());
	}
	
	public Days getDay()
	{
		return day;
	}

}
