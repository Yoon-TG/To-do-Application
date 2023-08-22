package com.example.todo.dto;

import com.example.todo.model.TodoEntity;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TodoDTO { //entity의 dto 버전 클래스 > todo 아이템 생성/수정/삭제
	private String id;
	private String title;
	private boolean done;
	
	public TodoDTO(final TodoEntity entity) {
		this.id = entity.getId();
		this.title = entity.getTitle();
		this.done = entity.isDone();
	}
	
	public static TodoEntity toEntity(final TodoDTO dto) {
		return TodoEntity.builder()
				.id(dto.getId())
				.title(dto.getTitle())
				.done(dto.isDone())
				.build();
	}
}