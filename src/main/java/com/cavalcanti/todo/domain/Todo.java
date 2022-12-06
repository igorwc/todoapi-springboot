package com.cavalcanti.todo.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity  
@Table(name = "category")
@Data
@EqualsAndHashCode(callSuper = false)
public class Todo implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.SEQUENCE, generator = "todo_seq")
	@SequenceGenerator(name = "todo_seq", allocationSize = 1, sequenceName = "todo_seq")
	private Long id;
 
	@Column(name = "title")
	private String title; 
	
	@Column(name = "description")
	private String description; 
	
	@Column(name = "deadline")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime deadline;
	
}
