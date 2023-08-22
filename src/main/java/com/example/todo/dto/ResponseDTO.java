package com.example.todo.dto;

import java.util.List;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseDTO<T> { //HTTP응답으로 사용할 DTO
	private String error;
	private List<T> data;	
	
}
