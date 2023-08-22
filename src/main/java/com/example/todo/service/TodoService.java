package com.example.todo.service;

import java.util.List;
import java.util.Optional;

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

	//create() 메서드 - 새로운 todo 아이템 생성 구현
	public List<TodoEntity> create(final TodoEntity entity){
		//validation
		validate(entity);

		//save
		repository.save(entity);

		log.info("Entity Id : {} is saved", entity.getId());

		return repository.findByUserId(entity.getUserId());
	}
	
	//retrieve() - todo 아이템 검색 메서드 구현
	public List<TodoEntity> retrieve(final String userId){
		return repository.findByUserId(userId);
	}
	
	//update() - Todo 업데이트
	public List<TodoEntity> update(final TodoEntity entity){
		validate(entity); //저장할 데이터의 유효성 검사
		
		//넘겨받은 엔티티 id로 TodoEntity 가져옴(엔티티 존재x 경우 업데이트 불가능)
		final Optional<TodoEntity> original = repository.findById(entity.getId());
		
		if(original.isPresent()) {
			//반환된 toodEntity가 존재할 경우)새 entity값으로 덮어 씌우기
			final TodoEntity todo = original.get();
			todo.setTitle(entity.getTitle());
			todo.setDone(entity.isDone());
			
			//db에 저장
			repository.save(todo);
		}
		
		//original&lambda 사용 ver.
//		original.ifPresent(todo -> {
//		//반환된 todoEntity가 존재할 경우)새 entity값으로 덮어 씌우기
//		todo.setTitle(entity.getId()); 
//		todo.setDone(entity.isDone());
//		
////		db에 저장
//		repository.save(todo);
//	});
		
		//retrieve() 이용 > 사용자의 모든 todo 리스트 리턴 (반영된 후의 결과까지 리턴)
		return retrieve(entity.getUserId());
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
