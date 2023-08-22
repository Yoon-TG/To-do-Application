package com.example.todo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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

	//PostMapping
	@PostMapping
	public ResponseEntity<?> createTodo(@RequestBody TodoDTO dto){
		try {
			String tempoId = "temporary-user";
			
			TodoEntity entity = TodoDTO.toEntity(dto);
			
			entity.setId(null);
			entity.setUserId(tempoId);
			
			List<TodoEntity> entities = service.create(entity);
			
			List<TodoDTO> dtos 
			= entities.stream().map(TodoDTO::new).collect(Collectors.toList());
			
			ResponseDTO<TodoDTO> reponse 
			= ResponseDTO.<TodoDTO>builder().data(dtos).build();
			
			return ResponseEntity.ok().body(reponse);			
		} catch (Exception e) {
			// TODO: handle exception
			String error = e.getMessage();
			
			ResponseDTO<TodoDTO> response 
			= ResponseDTO.<TodoDTO>builder().error(error).build();

			return ResponseEntity.badRequest().body(response);			
		}
		
	}

}
