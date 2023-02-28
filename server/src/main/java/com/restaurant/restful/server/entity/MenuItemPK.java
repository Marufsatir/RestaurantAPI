package com.restaurant.restful.server.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class MenuItemPK implements Serializable {
	
	private String name;
	
	private MenuPK menu;
	
	public MenuItemPK() {}
	
	public MenuItemPK(String name, MenuPK menu)
	{
		this.name = name;
		this.menu = menu;
	}
	
	public boolean equals(Object object)
	{
		if(object instanceof MenuItemPK)
		{
			MenuItemPK menuItemPK = (MenuItemPK) object;
			if(menuItemPK.name.equals(this.name) && menuItemPK.menu.equals(this.menu))
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
		return (int)(this.name.hashCode() +  this.menu.hashCode());
	}
}
