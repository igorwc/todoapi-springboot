package com.cavalcanti.todo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cavalcanti.todo.domain.Todo; 

public interface  TodoRepository extends JpaRepository<Todo, Long>{
	
	List<Todo> findByDescriptionContainingIgnoreCase(String description);
	List<Todo> findByTitleContainingIgnoreCase(String title);
}
