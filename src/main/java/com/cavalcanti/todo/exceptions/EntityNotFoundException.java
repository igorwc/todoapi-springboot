package com.cavalcanti.todo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends Exception {

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EntityNotFoundException(Long id) {
	        super("Entity not found with ID " + id);
	    }
}
