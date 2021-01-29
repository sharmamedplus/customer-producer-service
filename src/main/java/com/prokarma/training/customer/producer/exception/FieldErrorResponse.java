package com.prokarma.training.customer.producer.exception;

import java.util.Map;
import java.util.TreeSet;

public class FieldErrorResponse {
	private String message = "Invalid request.";
	private Map<String, TreeSet<String>> modelState;

	public Map<String, TreeSet<String>> getModelState() {
		return modelState;
	}

	public void setModelState(Map<String, TreeSet<String>> modelState) {
		this.modelState = modelState;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
