package br.com.ksgprod.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ksgprod.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
