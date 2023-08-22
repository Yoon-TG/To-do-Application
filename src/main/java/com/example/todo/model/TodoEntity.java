package com.example.todo.model;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="Todo")
public class TodoEntity {
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy="uuid")
	private String id; //오브젝트 아이디
	private String userId; //오브젝트 생성한 사용자의 아이디
	private String title; //todo 타이틀
	private boolean done; //true(완료)-false(미완)로 todo의 완료 여부를 확인함
}