package com.prokarma.training.customer.producer.service;

import org.apache.kafka.common.errors.TimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.prokarma.training.customer.kafka.domain.KafkaCustomerRequest;
import com.prokarma.training.customer.producer.converter.DefaultCustomerProducerRequestConverter;
import com.prokarma.training.customer.producer.domain.CustomerRequest;
import com.prokarma.training.customer.producer.domain.PublisherResponse;
import com.prokarma.training.customer.producer.exception.ApplicationRuntimeException;

@Service
public class DefaultCustomerProducerService implements CustomerProducerService {

	private static final Logger LOG = LoggerFactory.getLogger(DefaultCustomerProducerService.class);

	@Autowired
	private KafkaTemplate<String, KafkaCustomerRequest> kafkaTemplate;

	@Value("${kafka.topic}")
	private String topic;

	@Autowired
	private DefaultCustomerProducerRequestConverter defaultCustomerRequestConverter;

	@Override
	public PublisherResponse publishCustomerRequest(CustomerRequest customerRequest) {

		PublisherResponse response = null;
		long startingTime = System.currentTimeMillis();

		try {
			KafkaCustomerRequest kafkaCustomerRequest = defaultCustomerRequestConverter.convert(customerRequest);

			KafkaCustomerRequest maskedKafkaCustomerRequest = defaultCustomerRequestConverter
					.maskKafkaCustomerRequest(kafkaCustomerRequest);

			LOG.info("Masked Kafaka Customer Request : {}", maskedKafkaCustomerRequest);

			kafkaTemplate.send(topic, kafkaCustomerRequest);

			LOG.info("Data published in time : {} ms", System.currentTimeMillis() - startingTime);

			response = new PublisherResponse();
			response.setStatus("success");
			response.setMessage("Successfully published data");

		} catch (TimeoutException e) {
			throw new ApplicationRuntimeException("Error in publishing data");
		}

		return response;
	}
}
