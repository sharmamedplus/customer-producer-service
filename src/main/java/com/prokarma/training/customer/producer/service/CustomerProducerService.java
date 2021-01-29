package com.prokarma.training.customer.producer.service;

import com.prokarma.training.customer.producer.domain.CustomerRequest;
import com.prokarma.training.customer.producer.domain.PublisherResponse;

public interface CustomerProducerService {

	public PublisherResponse publishCustomerRequest(CustomerRequest customerRequest);
}
