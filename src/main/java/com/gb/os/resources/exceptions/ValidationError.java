package com.gb.os.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

import com.gb.os.service.exceptions.StandardError;

public class ValidationError extends StandardError {

	private static final long serialVerisionUID = 1L;

	private List<FieldMessage> errors = new ArrayList<>();

	public ValidationError() {
		super();
	}

	public ValidationError(Long timestamp, Integer status, String error) {
		super(timestamp, status, error);
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void addError(String fieldName, String message) {
		this.errors.add(new FieldMessage(fieldName, message));
	}

}
