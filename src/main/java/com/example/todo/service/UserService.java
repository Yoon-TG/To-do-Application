package com.example.todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todo.model.UserEntity;
import com.example.todo.persistence.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public UserEntity create(final UserEntity userEntity) { //계정 만들기
		if(userEntity == null || userEntity.getEmail() == null) { //만들어야 될 계정의 입력 정보가 비어있을 경우
			throw new RuntimeException("Invalid arguments");
		}
		
		final String email = userEntity.getEmail();
		if(userRepository.existsByEmail(email)) { //같은 email 존재 여부(중복 방지)
			log.warn("Email already exists {}",email);
			throw new RuntimeException("Enauk already exists");
		}
		
		return userRepository.save(userEntity);
	}
	
	public UserEntity getByCredentials(final String email, final String password) {
		return userRepository.findByEmailAndPassword(email, password);
	}
}
