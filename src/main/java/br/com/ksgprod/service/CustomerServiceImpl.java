package br.com.ksgprod.service;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.ksgprod.domain.Customer;
import br.com.ksgprod.domain.Status;
import br.com.ksgprod.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);
	
	private CustomerRepository repository;
	
	public CustomerServiceImpl(CustomerRepository repository) {
		this.repository = repository;
	}
	
	@PostConstruct
	private void populateDataBase() {
		
		Customer customer1 = new Customer();
		
		customer1.setEmail("joao@gmail.com");
		customer1.setName("João");
		customer1.setPhone("999999999999");
		
		Customer customer2 = new Customer();
		
		customer2.setEmail("maria@gmail.com");
		customer2.setName("Maria");
		customer2.setPhone("8888888888888");
		
		Customer customer3 = new Customer();
		
		customer3.setEmail("marcela@gmail.com");
		customer3.setName("Marcela");
		customer3.setPhone("7777777777777");
		
		Customer customer4 = new Customer();
		
		customer4.setEmail("carlos@gmail.com");
		customer4.setName("Carlos");
		customer4.setPhone("66666666666666");
		
		Customer customer5 = new Customer();
		
		customer5.setEmail("andre@gmail.com");
		customer5.setName("André");
		customer5.setPhone("55555555555555");
		
		this.repository.save(customer1);
		this.repository.save(customer2);
		this.repository.save(customer3);
		this.repository.save(customer4);
		this.repository.save(customer5);
		
	}

	@Override
	public Customer findById(Integer id) {
		
		LOGGER.info("stage=init method=CustomerServiceImpl.findById id={}", id);
		Optional<Customer> optionalCustomer = this.repository.findById(id);
		
		if(!optionalCustomer.isPresent()) {
			throw new RuntimeException();
		}
		
		Customer customer = optionalCustomer.get();
		
		LOGGER.info("stage=end method=CustomerServiceImpl.findById customer={}", customer);
		
		return customer;
	}

	@Override
	public List<Customer> findAll() {
		LOGGER.info("stage=init method=CustomerServiceImpl.findAll");
		
		List<Customer> customers = this.repository.findAll();
		
		LOGGER.info("stage=end method=CustomerServiceImpl.findAll customers={}", customers);
		
		return customers;
	}

	@Override
	public Status deleteById(Integer id) {
		
		LOGGER.info("stage=init method=CustomerServiceImpl.deleteById id={}", id);
		
		Status status = Status.FAILURE;
		
		try {
			this.repository.deleteById(id);
			status = Status.SUCCESS;
		}catch (Exception e) {
			LOGGER.error("stage=error method=CustomerServiceImpl.deleteById error={}", e.getMessage());
		}
		
		LOGGER.info("stage=end method=CustomerServiceImpl.deleteById status={}", status);
		
		return status;
	}

	@Override
	public Status insert(Customer customer) {
		
		LOGGER.info("stage=init method=CustomerServiceImpl.insert customer={}", customer);
		
		Status status = Status.FAILURE;
		
		try {
			this.repository.save(customer);
			status = Status.SUCCESS;
		}catch (Exception e) {
			LOGGER.error("stage=error method=CustomerServiceImpl.insert error={}", e.getMessage());
		}
		
		LOGGER.info("stage=end method=CustomerServiceImpl.insert status={}", status);
		
		return status;
	}

}
