package com.example.todo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.todo.dto.ResponseDTO;
import com.example.todo.dto.TestRequestBodyDTO;

@RestController
@RequestMapping("test")
public class TestController {

	//1. 일반 get 메서드로 접속 시
	@GetMapping
	public String testController() {
		return "Hello world";
	}

	//2.get 메서드 경로 지정
	@GetMapping("/testGetMapping")
	public String testGetMappingController() {
		return "testGetMapping";
	}

	//3. 매개변수 - pathvariable
	@GetMapping("/{id}")
	public String pathvariableController(@PathVariable(required=false) int id) {
		return "@PathVariable ID : "+id;
	}

	//4. 매개변수 - RequestParam
	@GetMapping("/testRequestParam")
	public String requestParamController(@RequestParam(required=false) int id) {
		return "@RequestParam ID : "+id;
	}

	//5. 매개변수 - RequestBody
	@GetMapping("/testRequestBody")
	public String requestBodyController(@RequestBody TestRequestBodyDTO tdto) {
		return "@RequestBody ID : "+tdto.getId()+" Message : "+tdto.getMessage();
	}

	//6. 매개변수 - ResponseBody
	@GetMapping("/testResponseBody")
	public ResponseDTO<String> responseBodyController() {
		List<String> list = new ArrayList<>();
		list.add("Hello Mr.my Yesterday");
		ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();

		return response;
	}

	//7. 매개변수 - ResponseEntity
	@GetMapping("/testResponseEntity")
	public ResponseEntity<?> responseEntityController() {
		List<String> list = new ArrayList<>();
		list.add("Hello Mr.my Entity");
		ResponseDTO<String> response 
		= ResponseDTO.<String>builder().data(list).build();

		return ResponseEntity.badRequest().body(response);
	}

}
