package com.example.todo.model;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
public class UserEntity {
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id; //사용자에게 고유하게 부여되는 id
	
	@Column(nullable = false)
	private String username; //사용자 이름
	
	@Column(nullable = false)
	private String email; //사용자의 email = 아이디와 같은 기능
	
	@Column(nullable = false)
	private String password; //비밀번호
	
	
	
	
	
	
	
	
	
	
	
}