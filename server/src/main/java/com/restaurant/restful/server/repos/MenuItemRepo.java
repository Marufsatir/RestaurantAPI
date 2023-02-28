package com.restaurant.restful.server.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.restaurant.restful.server.entity.Menu;
import com.restaurant.restful.server.entity.MenuItem;
import com.restaurant.restful.server.entity.MenuItemPK;

import jakarta.transaction.Transactional;

public interface MenuItemRepo extends JpaRepository<MenuItem, MenuItemPK>{
	List<MenuItem> findByMenu(Optional<Menu> menu);
	List<MenuItem> findByMenu(Menu menu);
	
	@Query(value="SELECT COUNT(*) FROM menuitem m WHERE  m.item_day= ?3 and m.menu_item_restaurant_id = ?2 and m.name in ?1", nativeQuery=true)
	Integer findTheMatches(List<String> userOrders, String restaurantId, Integer day);
	/*
	@Modifying
	@Query("DELETE FROM menuitem m WHERE  m.day=:?1 AND m.item_day=:?2")
	@Transactional
	void deleteMenu(Integer day, String restaurantId);
	*/
}