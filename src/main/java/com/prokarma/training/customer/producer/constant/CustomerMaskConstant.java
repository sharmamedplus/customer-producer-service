package com.prokarma.training.customer.producer.constant;

public enum CustomerMaskConstant {

	CUSTOMER_NUMBER_REGEX("\\d(?=(?:\\D*\\d){0,3}\\D*$)"), EMAIL_REGEX("[\\w\\W]{4}"), EMAIL_REPLACEMENT_CHAR("****"),
	BIRTHDATE_REGEX("[^-\\n](?=.*-[^-]*$)"), REPLACEMENT_CHARACTER("*");

	private String value;

	private CustomerMaskConstant(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}
}
