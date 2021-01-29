package com.prokarma.training.customer.producer.test.helper;

import com.prokarma.training.customer.producer.domain.CustomerAddress;
import com.prokarma.training.customer.producer.domain.CustomerRequest;
import com.prokarma.training.customer.producer.domain.CustomerStatus;

public class TestDataHelper {

	public static CustomerRequest getCustomerData() {
		
		CustomerRequest customerRequest = new CustomerRequest();
		customerRequest.setCustomerNumber("IN04852048");
		customerRequest.setFirstName("VijayKumar");
		customerRequest.setLastName("Sharma1234");
		customerRequest.setBirthdate("20-08-1991");
		customerRequest.setCountry("India");
		customerRequest.setCountryCode("IN");
		customerRequest.setMobileNumber("9398130143");
		customerRequest.setEmail("vijaysharma@gmail.com");
		customerRequest.setCustomerStatus(CustomerStatus.OPEN);

		CustomerAddress address = new CustomerAddress();
		address.setAddressLine1("Address Line1");
		address.setAddressLine2("Address Line2");
		address.setStreet("Street Name");
		address.setPostalCode("27459");

		customerRequest.setAddress(address);

		return customerRequest;
	}
}
