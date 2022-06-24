package br.com.ksgprod.converter;

import java.util.List;
import java.util.function.Function;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import br.com.ksgprod.domain.Customer;
import br.com.ksgprod.soap.wsdl.CustomerDetail;
import br.com.ksgprod.soap.wsdl.GetAllCustomerDetailResponse;

@Component
public class CustomerListConverter implements Function<List<Customer>, GetAllCustomerDetailResponse> {
	
	@Override
	public GetAllCustomerDetailResponse apply(List<Customer> customerList) {
		
		if(CollectionUtils.isEmpty(customerList)) return null;
		
		GetAllCustomerDetailResponse response = new GetAllCustomerDetailResponse();
		
		customerList.forEach(customer -> response.getCustomerDetail().add(this.convertDetail(customer)));
		
		return response;
	}

	private CustomerDetail convertDetail(Customer customer) {
		CustomerDetail customerDetail = new CustomerDetail();

		customerDetail.setId(customer.getId());
		customerDetail.setName(customer.getName());
		customerDetail.setPhone(customer.getPhone());
		customerDetail.setEmail(customer.getEmail());

		return customerDetail;
	}

}
