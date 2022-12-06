package com.cavalcanti.todo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequiredFieldException extends Exception {

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RequiredFieldException(String field) {
	        super("Required field not set: " + field);
	    }
}
