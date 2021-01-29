package com.prokarma.training.customer.producer.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Customer Account status
 */
public enum CustomerStatus {

	OPEN("Open"),

	CLOSED("Closed"),

	SUSPENDED("Suspended"),

	RESTORED("Restored");

	private String value;

	CustomerStatus(String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static CustomerStatus fromValue(String text) {
		for (CustomerStatus b : CustomerStatus.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}
}
