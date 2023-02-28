package com.restaurant.restful.server.entity;

import java.util.Set;

import jakarta.annotation.Nonnull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;


enum Days{
	MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}

enum CurrencyTypes{
	USD, EUR, JPY, GBP, TRY
}

@Entity
@Table(name="menu")
@IdClass(MenuPK.class)
@Data
public class Menu {
	
	@Id
	Days day;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@Id
	@JoinColumn(name="menu_restaurant_id", referencedColumnName="restaurant_id")
	Restaurant restaurant;
	
	
	@OneToMany(mappedBy="menu")
	private Set<MenuItem> items;
	
	@Nonnull
	CurrencyTypes currencyType;
	
	public Days getDay()
	{
		return day;
	}
	
	public void setDay(Integer id)
	{
		this.day = Days.values()[id];
	}
	
	public void setCurrencyType(Integer id)
	{
		this.currencyType = CurrencyTypes.values()[id];
	}
	
	public Integer getMenuInteger()
	{
		return this.day.ordinal();
	}
	
	public Integer getMenuCurrency()
	{
		return this.currencyType.ordinal();
	}
	
	public static Days getDay(Integer day)
	{
		return Days.values()[day];
	}
}
