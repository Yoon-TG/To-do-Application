package com.example.todo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todo.model.TodoEntity;
import com.example.todo.persistence.TodoRepository;

import lombok.extern.slf4j.Slf4j;

//import com.google.common.collect.Lists;


@Slf4j
@Service
public class TodoService {

	@Autowired
	private TodoRepository repository;

	//test Service1
	//	public String testService() {
	//		return "Test Service";
	//	}

	//test service2
	public String testService() {
		//엔티티 생성
		TodoEntity entity = TodoEntity.builder().title("My first todo item").build();
		//엔티티 저장
		repository.save(entity);
		//엔티티 검색
		TodoEntity savedEntity = repository.findById(entity.getId()).get();

		return savedEntity.getTitle();		
	}

	//create() 메서드 구현
	public List<TodoEntity> create(final TodoEntity entity){
		//validation
		validate(entity);

		//save
		repository.save(entity);

		log.info("Entity Id : {} is saved", entity.getId());

		return repository.findByUserId(entity.getUserId());
	}
	
	//retrieve() 메서드 구현
	public List<TodoEntity> retrieve(final String userId){
		return repository.findByUserId(userId);
	}

	
	//validation
	private void validate(final TodoEntity entity) {
		if(entity == null) {
			log.warn("Entity cannot be null");

			throw new RuntimeException("Entity cannot be null");
		}

		if(entity.getUserId() == null) {
			log.warn("Unknown user");

			throw new RuntimeException("Unknown user");
		}
	}
		
}
