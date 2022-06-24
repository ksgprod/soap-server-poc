package br.com.ksgprod.converter;

import java.util.Objects;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import br.com.ksgprod.domain.Customer;
import br.com.ksgprod.soap.wsdl.InsertCustomerRequest;

@Component
public class InsertCustomerConverter implements Function<InsertCustomerRequest, Customer> {

	@Override
	public Customer apply(InsertCustomerRequest request) {
		
		if(Objects.isNull(request)) return null;
		
		Customer customer = new Customer();
		
		customer.setId(request.getCustomerDetail().getId());
		customer.setEmail(request.getCustomerDetail().getEmail());
		customer.setName(request.getCustomerDetail().getName());
		customer.setPhone(request.getCustomerDetail().getPhone());
		
		return customer;
	}

}
