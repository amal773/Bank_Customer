package com.example.controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.exception.ResourceNotFoundException;
import com.example.model.Amount;
import com.example.model.Customer;
import com.example.model.Login;
import com.example.model.Password;
import com.example.model.Receiver;
import com.example.service.CustomerService;

@RestController
public class CustomerController {
	@Autowired
	RestTemplate rs;

	@Autowired
	CustomerService cs;

	@PostMapping("/login")
	public Customer login(@RequestBody Login obj) {
		Customer cust = cs.login(obj.getUsername(), obj.getPassword());
		return cust;
	}

	@PutMapping("/deposit/{id}")
	public void depositCheck(@PathVariable int id, @RequestBody Amount amt) throws ResourceNotFoundException{
		Optional.ofNullable(cs.getCustomerById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not found for the id " + id)));
		cs.depositCheck(id, amt.getAmount());
	}

	@PutMapping("/withdrawal/{id}")
	public void withdrawalCheck(@PathVariable int id, @RequestBody Amount amt) throws ResourceNotFoundException {
		Optional.ofNullable(cs.getCustomerById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not found for the id " + id)));
		cs.withdrawalCheck(id, amt.getAmount());
	}

	@GetMapping("/checkbalance/{id}")
	public float checkBalance(@PathVariable int id) throws ResourceNotFoundException {
		Optional.ofNullable(cs.getCustomerById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not found for the id " + id)));
		return cs.checkBalance(id);
	}

	@PutMapping("/changepassword/{id}")
	public void changePassword(@PathVariable int id, @RequestBody Password pass) throws ResourceNotFoundException {
		Optional.ofNullable(cs.getCustomerById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not found for the id " + id)));
		cs.changePassword(id, pass.getPassword());
	}

	@PutMapping("/updatecustomer/{id}")
	public void updateCustomer(@PathVariable int id, @RequestBody Customer cust) throws ResourceNotFoundException {
		Optional.ofNullable(cs.getCustomerById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not found for the id " + id)));
		cs.updateCustomer(id, cust);
	}
	
	@PutMapping("/transfer/{id}")
	public void transact(@PathVariable int id, @RequestBody Receiver rec) throws ResourceNotFoundException {
		Optional.ofNullable(cs.getCustomerById(rec.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Customer not found for the id " + rec.getId())));
		cs.transact(id, rec.getId(), rec.getAmount());
	}

}
