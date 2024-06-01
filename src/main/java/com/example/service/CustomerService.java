package com.example.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Customer;
import com.example.repo.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	CustomerRepository cr;
	
	public Optional<Customer> getCustomerById(int id) {
		return cr.findById(id);
	}

	public Customer login(String username, String password) {
		return cr.login(username, password);
	}

	public void depositCheck(int id, float amount) {
		Optional<Customer> cust = cr.findById(id);
		float bal = amount + cust.get().getBalance();
		cust.get().setBalance(bal);
		cr.save(cust.get());

	}

	public void withdrawalCheck(int id, float amount) {
		Optional<Customer> cust = cr.findById(id);
		float bal = cust.get().getBalance() - amount;
		cust.get().setBalance(bal);
		cr.save(cust.get());

	}

	public float checkBalance(int id) {
		Optional<Customer> cust = cr.findById(id);
		return cust.get().getBalance();
	}

	public void changePassword(int id, String password) {
		Optional<Customer> cust = cr.findById(id);

		cust.get().setPassword(password);
		cr.save(cust.get());
	}

	public void updateCustomer(int id, Customer cust) {
		Optional<Customer> oldCustomer = cr.findById(id);
		oldCustomer.get().setAddress(cust.getAddress());
		oldCustomer.get().setAge(cust.getAge());
		oldCustomer.get().setEmail(cust.getEmail());
		oldCustomer.get().setPhone(cust.getPhone());
		oldCustomer.get().setName(cust.getName());

		cr.save(oldCustomer.get());
	}

	public void transact(int id, int to, float amount) {
		withdrawalCheck(id, amount);
		depositCheck(to, amount);
	}

}
