package com.cavalcanti.todo.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.cavalcanti.todo.domain.Todo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class TodoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String description;
	private String title; 
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime deadline;

	public static TodoDTO fromModel(Todo todo) {
		TodoDTO obj = new TodoDTO();
		obj.setId(todo.getId());
		obj.setTitle(todo.getTitle());
		obj.setDeadline(todo.getDeadline());
		obj.setDescription(todo.getDescription());

		return obj;
	}

	public Todo toModel() {
		Todo obj = new Todo();
		obj.setId(this.getId());
		obj.setDeadline(this.getDeadline());
		obj.setTitle(this.getTitle());
		obj.setDescription(this.getDescription());

		return obj;

	}

}
