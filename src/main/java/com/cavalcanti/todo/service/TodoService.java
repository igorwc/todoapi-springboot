package com.cavalcanti.todo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cavalcanti.todo.domain.Todo;
import com.cavalcanti.todo.dto.TodoNewUpdateDTO;
import com.cavalcanti.todo.exceptions.EntityAlreadyExistsException;
import com.cavalcanti.todo.exceptions.EntityNotFoundException;
import com.cavalcanti.todo.exceptions.RequiredFieldException;
import com.cavalcanti.todo.repository.TodoRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TodoService {

	@Autowired
	private TodoRepository repository;

	public Todo create(TodoNewUpdateDTO dto)
			throws EntityAlreadyExistsException, RequiredFieldException {

		boolean entityExists = verifyIfExistsByTitle(dto.getTitle());
		log.info("Valor de entityExists: " + entityExists);
		if (entityExists) {
			throw new EntityAlreadyExistsException();
		}
		 

		Todo todo = new Todo(); 
		todo.setTitle(dto.getTitle());
		todo.setDescription(dto.getDescription());
		todo.setDeadline(dto.getDeadline());
		Todo savedTodo = repository.save(todo);
		return savedTodo;
	}

	public List<Todo> listAll() {
		List<Todo> allTodos = repository.findAll();
//		List<StackDTO> ret = new ArrayList<>(0);

//		ret = allCategories.stream().map(StackDTO::fromModel).collect(Collectors.toList());

		return allTodos;
	}

	public Todo findById(Long id) throws EntityNotFoundException {
		Todo todo = verifyIfExists(id);

		return todo;
	}

//	public List<Todo> findByName(String stack) throws EntityNotFoundException {
//		List<Stack> list = repository.findByNameIgnoreCase(stack);
//
//		return list.stream().map(StackDTO::fromModel).collect(Collectors.toList());
//	}

	private Todo verifyIfExists(Long id) throws EntityNotFoundException {
		return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
	}

	private Boolean verifyIfExistsByTitle(String title) {
		Boolean ret = false;
		List<Todo> list = repository.findByTitleContainingIgnoreCase(title);
		if (list == null || list.isEmpty()) {
			ret = false;
		} else {
			ret = true;
		}
		return ret;
	}

	public void delete(Long id) throws EntityNotFoundException {
		verifyIfExists(id);
		repository.deleteById(id);
	}

	public Todo updateById(Long id, TodoNewUpdateDTO dto) throws EntityNotFoundException {
		Todo todoUpdate = verifyIfExists(id);
		
		todoUpdate.setDeadline(dto.getDeadline());
		todoUpdate.setDescription(dto.getDescription());
		todoUpdate.setTitle(dto.getTitle());
 
		Todo updatedTodo = repository.save(todoUpdate);
		return updatedTodo;
	} 
}
