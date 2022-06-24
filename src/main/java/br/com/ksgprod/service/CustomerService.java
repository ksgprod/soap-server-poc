package br.com.ksgprod.service;

import java.util.List;

import br.com.ksgprod.domain.Customer;
import br.com.ksgprod.domain.Status;

public interface CustomerService {
	
	public Customer findById(Integer id);
	
	public List<Customer> findAll();
	
	public Status deleteById(Integer id);
	
	public Status insert(Customer customer);
	
}
