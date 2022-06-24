package br.com.ksgprod.converter;

import java.util.Objects;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import br.com.ksgprod.domain.Customer;
import br.com.ksgprod.soap.wsdl.CustomerDetail;
import br.com.ksgprod.soap.wsdl.GetCustomerDetailResponse;

@Component
public class CustomerConverter implements Function<Customer, GetCustomerDetailResponse> {

	@Override
	public GetCustomerDetailResponse apply(Customer customer) {

		if (Objects.isNull(customer))
			return null;

		GetCustomerDetailResponse response = new GetCustomerDetailResponse();

		response.setCustomerDetail(this.convertDetail(customer));

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
