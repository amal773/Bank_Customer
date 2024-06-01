package com.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	@Query(value = "select * from customer where username = ?1 and password = ?2", nativeQuery = true)
	public Customer login(String username, String password);
}
