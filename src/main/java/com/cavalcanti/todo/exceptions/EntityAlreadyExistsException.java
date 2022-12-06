package com.cavalcanti.todo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EntityAlreadyExistsException extends Exception {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	public EntityAlreadyExistsException() {
		super("Entity already exists");
	}
	public EntityAlreadyExistsException(String msg) {
		super(msg);
	}
}
