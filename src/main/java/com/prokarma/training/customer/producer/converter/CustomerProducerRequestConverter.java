package com.prokarma.training.customer.producer.converter;

public interface CustomerProducerRequestConverter<I, O> {
	O convert(I input);
}
