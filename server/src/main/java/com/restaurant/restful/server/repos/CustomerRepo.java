package com.restaurant.restful.server.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurant.restful.server.entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer, String> {

}
