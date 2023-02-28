package com.restaurant.restful.server.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.restaurant.restful.server.entity.Menu;
import com.restaurant.restful.server.entity.MenuPK;


public interface MenuRepo extends JpaRepository<Menu, MenuPK>{
	@Query(value = "SELECT * FROM menu WHERE day = ?1", nativeQuery = true)
	List<Menu> findByDay(Integer day);
}
