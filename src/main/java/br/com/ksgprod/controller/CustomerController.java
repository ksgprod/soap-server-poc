package br.com.ksgprod.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import br.com.ksgprod.converter.CustomerConverter;
import br.com.ksgprod.converter.CustomerListConverter;
import br.com.ksgprod.converter.InsertCustomerConverter;
import br.com.ksgprod.domain.Customer;
import br.com.ksgprod.domain.Status;
import br.com.ksgprod.service.CustomerServiceImpl;
import br.com.ksgprod.soap.wsdl.DeleteCustomerRequest;
import br.com.ksgprod.soap.wsdl.DeleteCustomerResponse;
import br.com.ksgprod.soap.wsdl.GetAllCustomerDetailRequest;
import br.com.ksgprod.soap.wsdl.GetAllCustomerDetailResponse;
import br.com.ksgprod.soap.wsdl.GetCustomerDetailRequest;
import br.com.ksgprod.soap.wsdl.GetCustomerDetailResponse;
import br.com.ksgprod.soap.wsdl.InsertCustomerRequest;
import br.com.ksgprod.soap.wsdl.InsertCustomerResponse;

@Endpoint
public class CustomerController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

	private static final String NAME_SPACE_URI = "http://ksgprod.com/soap-poc";

	private CustomerServiceImpl service;

	private CustomerConverter customerConverter;
	
	private CustomerListConverter customerListConverter;
	
	private InsertCustomerConverter insertCustomerConverter;

	public CustomerController(CustomerServiceImpl service, CustomerConverter customerConverter,
			CustomerListConverter customerListConverter, InsertCustomerConverter insertCustomerConverter) {
		
		this.service = service;
		this.customerConverter = customerConverter;
		this.customerListConverter = customerListConverter;
		this.insertCustomerConverter = insertCustomerConverter;
	}

	@PayloadRoot(namespace = NAME_SPACE_URI, localPart = "GetCustomerDetailRequest")
	@ResponsePayload
	public GetCustomerDetailResponse findById(@RequestPayload GetCustomerDetailRequest request) throws Exception {
		
		LOGGER.info("stage=init method=CustomerController.findById id={}", request.getId());

		Customer customer = this.service.findById(request.getId());
		
		LOGGER.info("stage=end method=CustomerController.findById customer={}", customer);
		
		return this.customerConverter.apply(customer);
	}

	@PayloadRoot(namespace = NAME_SPACE_URI, localPart = "GetAllCustomerDetailRequest")
	@ResponsePayload
	public GetAllCustomerDetailResponse findAll(
			@RequestPayload GetAllCustomerDetailRequest request) {
		
		LOGGER.info("stage=init method=CustomerController.findAll");
		
		List<Customer> customersList = this.service.findAll();
		GetAllCustomerDetailResponse response = this.customerListConverter.apply(customersList);
		
		LOGGER.info("stage=end method=CustomerController.findAll");
		
		return response;
	}

	@PayloadRoot(namespace = NAME_SPACE_URI, localPart = "DeleteCustomerRequest")
	@ResponsePayload
	public DeleteCustomerResponse deleteCustomer(@RequestPayload DeleteCustomerRequest request) {
		
		LOGGER.info("stage=init method=CustomerController.deleteCustomer id={}", request.getId());
		
		Status status = this.service.deleteById(request.getId());
		DeleteCustomerResponse response = new DeleteCustomerResponse();
		
		response.setStatus(status.getExternalStatus());
		
		LOGGER.info("stage=end method=CustomerController.deleteCustomer status={}", status);
		
		return response;
	}

	@PayloadRoot(namespace = NAME_SPACE_URI, localPart = "InsertCustomerRequest")
	@ResponsePayload
	public InsertCustomerResponse insertCustomerRequest(@RequestPayload InsertCustomerRequest request) {
		
		LOGGER.info("stage=init method=CustomerController.insertCustomerRequest");
		
		Customer customer = this.insertCustomerConverter.apply(request);
		Status status = this.service.insert(customer);
		InsertCustomerResponse response = new InsertCustomerResponse();
		
		response.setStatus(status.getExternalStatus());
		
		LOGGER.info("stage=end method=CustomerController.insertCustomerRequest status={}", status);
		
		return response;
	}

}
