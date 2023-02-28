package com.restaurant.restful.server.entity;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="Orders")
public class Orders {
	
	@Transient
	public static final String SEQUENCE_NAME = "user_sequence";
	@Id
	private Integer id;
	private Integer day;
	private String restaurantId;
	private String restaurantName;
	private String customerId;
	private Integer currencyType;
	private List<MenuItems> items;
	private Double cost;
}
