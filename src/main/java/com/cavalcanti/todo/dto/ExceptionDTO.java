package com.cavalcanti.todo.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class ExceptionDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer status;

	private String message;
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime time = LocalDateTime.now();

	public ExceptionDTO(Integer code, String message) {
		super();
		this.status =code;
		this.message = message;
	}

}
