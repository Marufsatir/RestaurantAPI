package com.restaurant.restful.server.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurant.restful.server.entity.User;

public interface UserRepo<T extends User> extends JpaRepository<T, String> {
	
}
