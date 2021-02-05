
package com.prokarma.training.customer.producer.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.prokarma.training.customer.kafka.domain.KafkaCustomerRequest;
import com.prokarma.training.customer.producer.test.helper.TestDataHelper;

@ExtendWith(MockitoExtension.class)
class DefaultCustomerProducerRequestConverterTest {

	@InjectMocks
	private DefaultCustomerProducerRequestConverter defaultCustomerRequestConverter;

	@Test
	void testConvert() {
		KafkaCustomerRequest request = defaultCustomerRequestConverter.convert(TestDataHelper.getCustomerData());
		assertEquals("IN04852048", request.getCustomerNumber());
	}

	@Test
	void testMaskedKafkaCustomerRequest() {
		KafkaCustomerRequest request = defaultCustomerRequestConverter
				.maskKafkaCustomerRequest(getKafkaCustomerRequestForMaskingOnly());
		assertEquals("IN0485****", request.getCustomerNumber());
		assertEquals("**-**-1991", request.getBirthdate());
		assertEquals("****ysharma@gmail.com", request.getEmail());
	}

	private KafkaCustomerRequest getKafkaCustomerRequestForMaskingOnly() {

		KafkaCustomerRequest customerRequest = new KafkaCustomerRequest();
		customerRequest.setCustomerNumber("IN04852048");
		customerRequest.setBirthdate("20-08-1991");
		customerRequest.setEmail("vijaysharma@gmail.com");

		return customerRequest;
	}

}
