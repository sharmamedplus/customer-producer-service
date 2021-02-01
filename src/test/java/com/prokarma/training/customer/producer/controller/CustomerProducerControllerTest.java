
package com.prokarma.training.customer.producer.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.prokarma.training.customer.producer.converter.DefaultCustomerProducerRequestConverter;
import com.prokarma.training.customer.producer.exception.GlobalExceptionHandler;
import com.prokarma.training.customer.producer.service.CustomerProducerService;
import com.prokarma.training.customer.producer.test.helper.TestDataHelper;
import com.prokarma.training.customer.producer.util.ObjectMapperUtil;

@ExtendWith(MockitoExtension.class)
class CustomerProducerControllerTest {

	@InjectMocks
	private CustomerProducerController customerProducerController;

	@Mock
	private CustomerProducerService customerProducerService;

	@Mock
	private DefaultCustomerProducerRequestConverter customerProducerRequestConverter;

	private MockMvc mockMvc;

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(customerProducerController)
				.setControllerAdvice(new GlobalExceptionHandler()).build();
	}

	@Test
	void testPublishCustomerData() throws Exception {
		mockMvc.perform(post("/customer/publish").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(ObjectMapperUtil.returnJsonFromObject(TestDataHelper.getCustomerData()))
				.headers(buildHttpRequestHeaders())).andExpect(status().is(HttpStatus.OK.value()));
	}

	@Test
	void testPublishCustomerDataWhenWrongUrlProvidedThenReturn404StatusCode() throws Exception {
		mockMvc.perform(post("/customer/publish/v1").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(ObjectMapperUtil.returnJsonFromObject(TestDataHelper.getCustomerData()))
				.headers(buildHttpRequestHeaders())).andExpect(status().is(HttpStatus.NOT_FOUND.value()));
	}

	private HttpHeaders buildHttpRequestHeaders() {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("Content-Type", "application/json");
		httpHeaders.set("Authorization", "bearer 98f70d7e-ec69-48b3-8f4f-018756f23709");
		httpHeaders.set("Activity-Id", "customer-publish-activity");
		httpHeaders.set("Transaction-Id", "customer-transaction");
		return httpHeaders;
	}
}
