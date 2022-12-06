package com.cavalcanti.todo.controller.api;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cavalcanti.todo.domain.Todo;
import com.cavalcanti.todo.dto.ExceptionDTO;
import com.cavalcanti.todo.dto.TodoDTO;
import com.cavalcanti.todo.dto.TodoNewUpdateDTO;
import com.cavalcanti.todo.exceptions.EntityAlreadyExistsException;
import com.cavalcanti.todo.exceptions.EntityNotFoundException;
import com.cavalcanti.todo.exceptions.RequiredFieldException;
import com.cavalcanti.todo.service.TodoService;
//https://www.baeldung.com/exception-handling-for-rest-with-spring
@RestController
@RequestMapping("/api")
public class TodoController {
	
	@Autowired
	private TodoService todoService;
	
	@GetMapping
	public ResponseEntity<?> list() {
		List<Todo> list = todoService.listAll() ;
		List<TodoDTO> ret = new ArrayList<>();
		if(!list.isEmpty()) {
//			ret = allCategories.stream().map(StackDTO::fromModel).collect(Collectors.toList());
			ret = list.stream().map(TodoDTO::fromModel).collect(Collectors.toList());
		}
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id) {
		Todo todo;
		try {
			todo = todoService.findById(id);
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>(
					 new ExceptionDTO(HttpStatus.NOT_FOUND.value(), e.getMessage()), 
			          HttpStatus.NOT_FOUND);
		}
		 
		return new ResponseEntity<>(todo, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> add(@RequestBody TodoNewUpdateDTO dto) {
		Todo todo = null;
		try {
			 todo = todoService.create(dto);
		} catch (EntityAlreadyExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>(
			          "Entity alredy exists", 
			          HttpStatus.BAD_REQUEST);
		}
		catch (RequiredFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>(
			          "Required field missing", 
			          HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(TodoDTO.fromModel(todo), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody TodoNewUpdateDTO dto) {
		Todo todo = null;
		try {
			 todo = todoService.updateById(id, dto);
		}
		catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>(
					 new ExceptionDTO(HttpStatus.NOT_FOUND.value(), e.getMessage()), 
			          HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(TodoDTO.fromModel(todo), HttpStatus.OK);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id ) {
		 
		try {
			  todoService.delete(id);
		}
		catch (EntityNotFoundException e) { 
			e.printStackTrace();
			return new ResponseEntity<>(
					 new ExceptionDTO(HttpStatus.NOT_FOUND.value(), e.getMessage()), 
			          HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
