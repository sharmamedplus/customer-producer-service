package com.prokarma.training.customer.producer.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prokarma.training.customer.producer.domain.CustomerRequest;
import com.prokarma.training.customer.producer.domain.PublisherResponse;
import com.prokarma.training.customer.producer.service.CustomerProducerService;

@RestController
@RequestMapping("/customer")
public class CustomerProducerController {

	private static final Logger LOG = LoggerFactory.getLogger(CustomerProducerController.class);

	@Autowired
	CustomerProducerService customerProducerService;

	@PostMapping("/publish")
	public ResponseEntity<PublisherResponse> publishCustomer(
			@RequestHeader(name = "Authorization", required = true) String authorization,
			@RequestHeader(name = "Activity-Id", required = true) String activityId,
			@RequestHeader(name = "Transaction-Id", required = true) String transactionId,
			@Valid @RequestBody CustomerRequest customerRequest) {

		LOG.info("Request Body : {}", customerRequest);

		PublisherResponse response = customerProducerService.publishCustomerRequest(customerRequest);

		LOG.info("Response Body : {}", response);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
