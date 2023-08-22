package com.example.todo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.DeleteExchange;

import com.example.todo.dto.ResponseDTO;
import com.example.todo.dto.TestRequestBodyDTO;
import com.example.todo.dto.TodoDTO;
import com.example.todo.model.TodoEntity;
import com.example.todo.service.TodoService;

@RestController
@RequestMapping("todo")
public class TodoController {

	@Autowired
	private TodoService service;
	
//	//testTodo 메서드 작성
//	@GetMapping("/testTodo")
//	public ResponseEntity<?> testTodo(){
//		List<String> list = new ArrayList<>();
//		list.add("This is ResponseEntity Body");
//
//		ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
//
//		return ResponseEntity.badRequest().body(response);
//	}

//	//service 테스트용 메서드 작성
//	@GetMapping("/testService")
//	public ResponseEntity<?> testService(){
//		String str = service.testService(); //테스트 서비스 사용
//		
//		List<String> list = new ArrayList<>();
//		list.add(str);
//		
//		ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
//		
//		return ResponseEntity.ok().body(response);
//	}

	@PostMapping
	public ResponseEntity<?> createTodo(@RequestBody TodoDTO dto){ //create()
		try {
			String tempoId = "temporary-user";
			
			TodoEntity entity = TodoDTO.toEntity(dto);
			
			entity.setId(null);
			entity.setUserId(tempoId);
			
			List<TodoEntity> entities = service.create(entity);
			
			List<TodoDTO> dtos 
			= entities.stream().map(TodoDTO::new).collect(Collectors.toList());
			
			ResponseDTO<TodoDTO> response 
			= ResponseDTO.<TodoDTO>builder().data(dtos).build();
			
			return ResponseEntity.ok().body(response);			
		} catch (Exception e) {
			// TODO: handle exception
			String error = e.getMessage();
			
			ResponseDTO<TodoDTO> response 
			= ResponseDTO.<TodoDTO>builder().error(error).build();

			return ResponseEntity.badRequest().body(response);			
		}
		
	}
	
	@GetMapping
	public ResponseEntity<?> retrieveTodoList(){ //retrieve()
		String tempoUserId = "temporary-user";
		
		List<TodoEntity> entities = service.retrieve(tempoUserId); //todo 리스트 가져옴(해당 아이디의)
		
		List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList()); //스트림 이용, 리턴된 엔티티 리스트를 todoDTO 리스트로 변환
		
		ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build(); //위의 todoDTO 리스트로 ResponseDTO 초기화
		
		return ResponseEntity.ok().body(response); //responseDTO 리턴
	}
	
	
	@PutMapping
	public ResponseEntity<?> updateTodo(@RequestBody TodoDTO dto){ //update()
		String tempoUserId = "temporary-user";
		
		TodoEntity entity = TodoDTO.toEntity(dto); //dto > entity로 변환
		
		entity.setUserId(tempoUserId); //id 초기화
		
		
		List<TodoEntity> entities = service.update(entity); //서비스의 update() 이용 - entity 업데이트		
		
		List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList()); //스트림 이용, 리턴된 엔티티 리스트를 todoDTO 리스트로 변환
		
		ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build(); //위의 todoDTO 리스트로 ResponseDTO 초기화
		
		return ResponseEntity.ok().body(response); //responseDTO 리턴
	}
	
	@DeleteMapping
	public ResponseEntity<?> deleteTodo(@RequestBody TodoDTO dto){ //delete()
		try {
		String tempoUserId = "temporary-user";
		
		TodoEntity entity = TodoDTO.toEntity(dto); //dto > entity로 변환
		
		entity.setUserId(tempoUserId); //임시 사용자 아이디 설정		
		
		List<TodoEntity> entities = service.delete(entity); //서비스의 delete() 이용 - entity delete
		
		List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList()); //스트림 이용, 리턴된 엔티티 리스트를 todoDTO 리스트로 변환
		
		ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build(); //위의 todoDTO 리스트로 ResponseDTO 초기화
		
		return ResponseEntity.ok().body(response); //responseDTO 리턴
		}catch (Exception e) { //예외발생 시 dto 대신 error에 메시지를 넣고 리턴
			// TODO: handle exception
			String error = e.getMessage();
			
			ResponseDTO<TodoDTO> response =  ResponseDTO.<TodoDTO>builder().error(error).build(); 
			return ResponseEntity.badRequest().body(response);
		}
	}

}
