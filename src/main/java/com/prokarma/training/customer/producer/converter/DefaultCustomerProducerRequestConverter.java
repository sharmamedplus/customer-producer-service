package com.prokarma.training.customer.producer.converter;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.prokarma.training.customer.producer.constant.CustomerMaskConstant;
import com.prokarma.training.customer.producer.domain.CustomerRequest;
import com.prokarma.training.customer.producer.kafka.domain.KafkaCustomerRequest;

@Component
public class DefaultCustomerProducerRequestConverter
		implements CustomerProducerRequestConverter<CustomerRequest, KafkaCustomerRequest> {

	@Override
	public KafkaCustomerRequest convert(CustomerRequest customerRequest) {

		KafkaCustomerRequest kafkaCustomerRequest = new KafkaCustomerRequest();
		kafkaCustomerRequest.setCustomerNumber(customerRequest.getCustomerNumber());
		kafkaCustomerRequest.setFirstName(customerRequest.getFirstName());
		kafkaCustomerRequest.setLastName(customerRequest.getLastName());
		kafkaCustomerRequest.setEmail(customerRequest.getEmail());
		kafkaCustomerRequest.setMobileNumber(customerRequest.getMobileNumber());
		kafkaCustomerRequest.setBirthdate(customerRequest.getBirthdate());
		kafkaCustomerRequest.setCountry(customerRequest.getCountry());
		kafkaCustomerRequest.setCountryCode(customerRequest.getCountryCode());
		kafkaCustomerRequest.setAddress(customerRequest.getAddress().toString());
		kafkaCustomerRequest.setCustomerStatus(String.valueOf(customerRequest.getCustomerStatus().name()));
		return kafkaCustomerRequest;
	}

	public KafkaCustomerRequest maskKafkaCustomerRequest(KafkaCustomerRequest kafkaCustomerRequest) {

		KafkaCustomerRequest maskedKafkaCustomerRequest = new KafkaCustomerRequest();

		BeanUtils.copyProperties(kafkaCustomerRequest, maskedKafkaCustomerRequest);

		String replacementCharacter = CustomerMaskConstant.REPLACEMENT_CHARACTER.getValue();
		
		maskedKafkaCustomerRequest.setCustomerNumber(kafkaCustomerRequest.getCustomerNumber().replaceAll(
				CustomerMaskConstant.CUSTOMER_NUMBER_REGEX.getValue(), replacementCharacter));

		maskedKafkaCustomerRequest
				.setEmail(kafkaCustomerRequest.getEmail().replaceFirst(CustomerMaskConstant.EMAIL_REGEX.getValue(),
						CustomerMaskConstant.EMAIL_REPLACEMENT_CHAR.getValue()));

		maskedKafkaCustomerRequest.setBirthdate(
				kafkaCustomerRequest.getBirthdate().replaceAll(CustomerMaskConstant.BIRTHDATE_REGEX.getValue(),
						replacementCharacter));

		return maskedKafkaCustomerRequest;
	}

}
