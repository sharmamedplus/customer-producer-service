
package com.prokarma.training.customer.producer.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.apache.kafka.common.errors.TimeoutException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import com.prokarma.training.customer.producer.converter.DefaultCustomerProducerRequestConverter;
import com.prokarma.training.customer.producer.domain.CustomerRequest;
import com.prokarma.training.customer.producer.domain.PublisherResponse;
import com.prokarma.training.customer.producer.exception.ApplicationRuntimeException;
import com.prokarma.training.customer.producer.test.helper.TestDataHelper;

@ExtendWith(MockitoExtension.class)
class DefaultCustomerPublisherServiceTest {

	@InjectMocks
	private DefaultCustomerProducerService defautCustomerProducerService;

	@Mock
	private KafkaTemplate<String, CustomerRequest> kafkaTemplate;

	@Mock
	private DefaultCustomerProducerRequestConverter defaultCustomerRequestConverter;

	@Test
	void testInvokeCustomerResponse() {

		PublisherResponse publisherResponse = defautCustomerProducerService.publishCustomerRequest(TestDataHelper.getCustomerData());

		assertEquals("success", publisherResponse.getStatus());
	}

	@Test
	void testInvokeCustomerResponseWithError() {

		Mockito.doThrow(new TimeoutException()).when(kafkaTemplate).send(Mockito.any(), Mockito.any());

		assertThrows(ApplicationRuntimeException.class, () -> defautCustomerProducerService.publishCustomerRequest(null));
	}
}
